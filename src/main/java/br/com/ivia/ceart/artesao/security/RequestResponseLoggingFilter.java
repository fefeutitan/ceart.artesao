package br.com.ivia.ceart.artesao.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.com.ivia.ceart.artesao.model.PerfilAcesso;
import br.com.ivia.ceart.artesao.model.Pessoa;
import br.com.ivia.ceart.artesao.model.PessoaPerfil;
import br.com.ivia.ceart.artesao.model.RecursoAcao;
import br.com.ivia.ceart.artesao.repository.PerfilAcessoRepository;
import br.com.ivia.ceart.artesao.repository.PessoaRepository;
import br.com.ivia.ceart.artesao.service.RecursoAcaoService;
import br.com.ivia.ceart.artesao.to.PerfilAcessoTO;
import br.com.ivia.ceart.artesao.to.PessoaTO;
import br.com.ivia.ceart.artesao.to.RecursoAcaoTO;
import br.com.ivia.ceart.artesao.util.enums.SituacaoPessoa;
import br.com.ivia.ceart.artesao.util.specification.PerfilAcessoSpecification;
import br.com.ivia.ceart.artesao.util.specification.PessoaSpecification;
import br.com.ivia.ceart.artesao.model.Perfil;

@Component
@Order(1)
public class RequestResponseLoggingFilter implements Filter {
 
	private final static Logger LOG = LoggerFactory.getLogger(RequestResponseLoggingFilter.class);

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private RecursoAcaoService recursoAcaoService;
	
    @Autowired
    private JwtValidator validator;
    
	@Autowired
	private PerfilAcessoRepository perfilAcessoRepository;
	
	@Override
	public void init(final FilterConfig filterConfig) throws ServletException {
		LOG.info("Initializing filter :{}", this);
	}

	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		LOG.info("Logging Request  {} : {}", req.getMethod(), req.getRequestURI());
		LOG.info("Logging Response :{}", res.getContentType());
	
		/*
        if(req.getMethod().equals("OPTIONS")) {
        	chain.doFilter(request, response);
        	return;
        }
        */
        
        String servletPath = req.getServletPath();
        String[] recursoAcaoArray = servletPath.split("/");
        
        String recursoCodigo = recursoAcaoArray[0].isEmpty() ? recursoAcaoArray[1] : recursoAcaoArray[0];
        String acaoCodigo = recursoAcaoArray[0].isEmpty() ? recursoAcaoArray[2] : recursoAcaoArray[1] ;
        
        RecursoAcaoTO recursoAcaoTO = new RecursoAcaoTO();
        recursoAcaoTO.setRecursoCodigo(recursoCodigo);
        recursoAcaoTO.setAcaoCodigo(acaoCodigo);
        List<RecursoAcao> recursos = recursoAcaoService.findAllBy(recursoAcaoTO);
        if(recursos.isEmpty()) {
        	chain.doFilter(request, response);
        	return;
        }
        
        String header = req.getHeader("authorization");
        if (header == null || !header.startsWith("Token ")) {
			setError(res, "Token não enviado!");
			return;
        }
        
        String token = header.substring(6);
		PessoaTO pessoaTO = validator.validate(token);
        if (pessoaTO == null) {
			setError(res, "Token inválido!");
			return;
        }
        
        Pessoa pessoa = new Pessoa();
		PessoaSpecification specification = new PessoaSpecification(pessoaTO);
		List<Pessoa> listPessoa = pessoaRepository.findAll(specification);
		if (listPessoa.isEmpty()) {
			setError(res, "Não foi possível encontrar o registro da pessoa!");
			return;
		}else {
			pessoa = listPessoa.get(0);
			if(!pessoaTO.getSenha().equals(pessoa.getSenha())) {
				setError(res, "Senha incorreta!");
				return;
			}else if(!pessoa.getSituacaoPessoa().equals(SituacaoPessoa.ATIVADA)) {
				setError(res, "Pessoa não está ativa!");
				return;
			}else if(pessoa.getPerfis() == null) {
				setError(res, "Pessoa não possui perfil definido!");
				return;
			}
		}
        
        if(!recursoCodigo.isEmpty() && !acaoCodigo.isEmpty()) {
			PerfilAcessoTO perfilAcessoTO = new PerfilAcessoTO();
			perfilAcessoTO.setRecursoAcaoId(recursos.get(0).getId());
			
			if(pessoa.getPerfis() != null) {
				List<Perfil> listAux = new ArrayList<>();
				Calendar c = Calendar.getInstance();
				c.set(Calendar.HOUR, 0);
				c.set(Calendar.MINUTE, 0);
				c.set(Calendar.SECOND, 0);
		        c.set(Calendar.MILLISECOND, 0);
				Date dataAtual = c.getTime();
				
				for (int i = 0; i < pessoa.getPerfis().size(); i++){
					PessoaPerfil pessoaPerfil = pessoa.getPerfis().get(i);
					Boolean valido = false;
					if(pessoaPerfil.getDtInicio().before(dataAtual) || pessoaPerfil.getDtInicio().equals(dataAtual)) {
						if(pessoaPerfil.getDtFim() == null || (pessoaPerfil.getDtFim().after(dataAtual) || pessoaPerfil.getDtFim().equals(dataAtual))) {
							valido = true;
						}
					}
					
					if(valido) {
						listAux.add(pessoaPerfil.getPerfil());
					}
				}
				
				Integer[] perfis  = new Integer[listAux.size()];
				if(!listAux.isEmpty()) {
					Integer i = 0;
					for(Perfil perfil : listAux) {
						perfis[i] = perfil.getId();
						i++;
					}
				}else {
					setError(res, "Pessoa não possui perfil ativo na data atual!");
					return;
				}
				
				perfilAcessoTO.setPerfis(perfis);
			}
			
			PerfilAcessoSpecification perfilAcessoSpecification = new PerfilAcessoSpecification(perfilAcessoTO);
			List<PerfilAcesso> listPerfilAcesso = perfilAcessoRepository.findAll(perfilAcessoSpecification);
			if (!listPerfilAcesso.isEmpty()) {
				Boolean acesso = false;
				for(PerfilAcesso perfilAcesso : listPerfilAcesso) {
					if(perfilAcesso.getAcesso()) {
						acesso = true;
						break;
					}
				}
				
				if(!acesso) {
					setError(res, "Sem permissão para executar a ação!");
					return;
				}
			}else {
				setError(res, "Sem permissão para executar a ação!");
				return;
			}
		}
		
		chain.doFilter(request, response);
	} 

	@Override
	public void destroy() {
		LOG.warn("Destructing filter :{}", this);
	}
	
	private void setError(HttpServletResponse res, String message) throws IOException, ServletException {
		res.sendError(403, message);
	}
}
