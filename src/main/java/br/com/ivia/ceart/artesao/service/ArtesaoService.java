package br.com.ivia.ceart.artesao.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import br.com.ivia.ceart.artesao.model.Artesao;
import br.com.ivia.ceart.artesao.model.ArtesaoAntigo;
import br.com.ivia.ceart.artesao.model.ArtesaoAntigoPessoaFisica;
import br.com.ivia.ceart.artesao.model.ArtesaoAntigoTipologiaTecnica;
import br.com.ivia.ceart.artesao.model.ArtesaoEdital;
import br.com.ivia.ceart.artesao.model.ArtesaoEntidade;
import br.com.ivia.ceart.artesao.model.HistoricoAcaoArtesao;
import br.com.ivia.ceart.artesao.model.HistoricoCarteiraArtesao;
import br.com.ivia.ceart.artesao.model.HistoricoSituacaoArtesao;
import br.com.ivia.ceart.artesao.model.MotivoSituacao;
import br.com.ivia.ceart.artesao.model.TesteHabilidade;
import br.com.ivia.ceart.artesao.repository.ArtesaoRepository;
import br.com.ivia.ceart.artesao.repository.HistoricoAcaoArtesaoRepository;
import br.com.ivia.ceart.artesao.repository.HistoricoCarteiraArtesaoRepository;
import br.com.ivia.ceart.artesao.repository.HistoricoSituacaoArtesaoRepository;
import br.com.ivia.ceart.artesao.to.ArtesaoTO;
import br.com.ivia.ceart.artesao.to.MessageTO;
import br.com.ivia.ceart.artesao.to.MotivoSituacaoTO;
import br.com.ivia.ceart.artesao.to.TesteHabilidadeTO;
import br.com.ivia.ceart.artesao.util.enums.Situacao;
import br.com.ivia.ceart.artesao.util.enums.TipoArtesao;
import br.com.ivia.ceart.artesao.util.exception.ArtesaoException;
import br.com.ivia.ceart.artesao.util.specification.ArtesaoSpecification;

@Service
public class ArtesaoService {

	@Autowired
	private ArtesaoRepository repository;

	@Autowired
	private TesteHabilidadeService testeHabilidadeService;

	@Autowired
	private ArtesaoAntigoService artesaoAntigoService;

	@Autowired
	private ArtesaoAntigoPessoaFisicaService artesaoAntigoPessoaFisicaService;

	@Autowired
	private HistoricoSituacaoArtesaoRepository histSitRepository;

	@Autowired
	private HistoricoAcaoArtesaoRepository historicoAcaoArtesaoRepository;

	@Autowired
	private HistoricoCarteiraArtesaoRepository historicoCarteiraArtesaoRepository;

	@Value("${acao.incluirDados}")
	private Integer incluirDados;

	@Value("${acao.alterarDados}")
	private Integer alterarDados;

	@Value("${acao.alterarSituacao}")
	private Integer alterarSituacao;

	@Value("${params.quantAnosValidadeCarteira}")
	private Integer quantAnosValidadeCarteira;

	@Value("${acao.renovacaoCarteira}")
	private Integer renovacaoCarteira;
	
	@Value("${acao.impressaoCarteira}")
	private Integer impressaoCarteira;	

	public List<Artesao> findAll() {
		List<Artesao> tipos = Lists.newArrayList(repository.findAll());
		if (tipos == null || tipos.isEmpty()) {
			throw new ArtesaoException("Erro ao procurar os artesãos.");
		}
		return tipos;
	}

	public MessageTO findAll(ArtesaoTO artesao) {
		ArtesaoSpecification specification = new ArtesaoSpecification(artesao);
		PageRequest pageRequest = PageRequest.of(artesao.getPageNumber() - 1, artesao.getPageSize(),
				new Sort(artesao.getDirection(), artesao.getBy()));
		Page<Artesao> page = repository.findAll(specification, pageRequest);
		List<Artesao> artesaoContent = page.getContent();
		return new MessageTO("Artesãos encontrados", true, page.getTotalPages(), artesaoContent);
	}

	public List<Artesao> findAllBy(ArtesaoTO artesao) {
		ArtesaoSpecification specification = new ArtesaoSpecification(artesao);
		return repository.findAll(specification);
	}

	public Artesao findById(Integer id) {
		Optional<Artesao> artesao = repository.findById(id);
		if (!artesao.isPresent()) {
			throw new ArtesaoException("Erro ao procurar o Artesao");
		}
		return artesao.get();
	}

	public void validarIntegracao(Artesao entidade) {
		if (entidade.getId() == null) {
			ArtesaoTO artesaoTO = new ArtesaoTO();
			artesaoTO.setCpf(entidade.getCpf());
			List<Artesao> list = findAllBy(artesaoTO);
			if (!list.isEmpty()) {
				throw new ArtesaoException("CPF já existente no banco de dados de artesãos.");
			}
		}

		TesteHabilidadeTO habilidadeTO = new TesteHabilidadeTO();
		habilidadeTO.setCpf(entidade.getCpf());
		List<TesteHabilidade> testes = testeHabilidadeService.findAll(habilidadeTO);
		if (testes.isEmpty()) {
			throw new ArtesaoException("Não existe Teste de Habilidade com o CPF informado.");
		}

		Boolean testeHabilitado = false;
		for (TesteHabilidade item : testes) {
			if (item.getHabilitado()) {
				testeHabilitado = true;
				break;
			}
		}
		if (!testeHabilitado) {
			throw new ArtesaoException("O CPF informado, não possui produto habilitado nos testes de habilidade.");
		}
	}

	@Transactional
	public Artesao integrar(Artesao entidade) {
		if (entidade.getEntidades() != null) {
			for (ArtesaoEntidade artesaoEntidade : entidade.getEntidades()) {
				artesaoEntidade.setArtesao(entidade);
			}
		}
		if (entidade.getEditais() != null) {
			for (ArtesaoEdital artesaoEdital : entidade.getEditais()) {
				artesaoEdital.setArtesao(entidade);
			}
		}

		Date dataAtual = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dataAtual);
		calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + quantAnosValidadeCarteira);

		TesteHabilidadeTO habilidadeTO = new TesteHabilidadeTO();
		habilidadeTO.setCpf(entidade.getCpf());
		List<TesteHabilidade> testes = testeHabilidadeService.findAll(habilidadeTO);

		/**** INICIO DA ROTINA DE INTEGRAÇÃO COM SISTEMA ATUAL ******/
		boolean insert = false;
		if (entidade.getId() == null) {
			insert = true;
		}

		ArtesaoAntigo artesaoAntigo = artesaoAntigoService.save(converterArtesao(entidade));
		ArtesaoAntigoPessoaFisica artesaoAntigoPessoaFisica = new ArtesaoAntigoPessoaFisica();
		if (insert) {
			entidade.setViaCarteira(1);
			entidade.setDataValidadeCarteira(calendar.getTime());
			entidade.setDataAtualizacaoCarteira(new Date());

			entidade.setId(artesaoAntigo.getId());
			if (!testes.isEmpty()) {
				List<ArtesaoAntigoTipologiaTecnica> produtos = new ArrayList<>();
				for (TesteHabilidade teste : testes) {
					ArtesaoAntigoTipologiaTecnica artesaoAntigoTipologiaTecnica = converterProduto(teste);
					artesaoAntigoTipologiaTecnica.setArtesaoAntigoPessoaFisica(artesaoAntigoPessoaFisica);
					produtos.add(artesaoAntigoTipologiaTecnica);
				}
				artesaoAntigoPessoaFisica.setProdutos(produtos);
			}
		} else {
			artesaoAntigoPessoaFisica = artesaoAntigoPessoaFisicaService.findById(entidade.getId());
		}
		converterArtesaoPessoaFisica(entidade, artesaoAntigoPessoaFisica);
		artesaoAntigoPessoaFisicaService.save(artesaoAntigoPessoaFisica);
		/***** FIM DA ROTINA DE INTEGRAÇÃO COM SISTEMA ATUAL ********/
		entidade = repository.save(entidade);

		HistoricoAcaoArtesao hist = new HistoricoAcaoArtesao();
		hist.setArtesao(entidade.getId());
		hist.setData(new Date());
		if (insert) {
			hist.setRecursoAcao(incluirDados);
		} else {
			hist.setRecursoAcao(alterarDados);
		}
		hist.setUsuario(1);
		historicoAcaoArtesaoRepository.save(hist);		
		
		if (insert) {
			HistoricoCarteiraArtesao historicoCarteiraArtesao = new HistoricoCarteiraArtesao();
			historicoCarteiraArtesao.setArtesaoId(entidade.getId());
			historicoCarteiraArtesao.setViaCarteira(1);
			historicoCarteiraArtesao.setDataAtualizacao(new Date());

			historicoCarteiraArtesao.setDataValidade(calendar.getTime());
			historicoCarteiraArtesao.setUsuarioId(1);
			historicoCarteiraArtesaoRepository.save(historicoCarteiraArtesao);
		}

		return entidade;
	}

	public Artesao save(Artesao entidade) {
		if (entidade.getId() == null) {
			ArtesaoTO artesaoTO = new ArtesaoTO();
			artesaoTO.setCpf(entidade.getCpf());
			List<Artesao> list = findAllBy(artesaoTO);
			if (!list.isEmpty()) {
				throw new ArtesaoException("CPF já existente no banco de dados de artesãos.");
			}
		}

		if (entidade.getEntidades() != null) {
			for (ArtesaoEntidade artesaoEntidade : entidade.getEntidades()) {
				artesaoEntidade.setArtesao(entidade);
			}
		}

		TesteHabilidadeTO habilidadeTO = new TesteHabilidadeTO();
		habilidadeTO.setCpf(entidade.getCpf());
		List<TesteHabilidade> list = testeHabilidadeService.findAll(habilidadeTO);
		if (list.isEmpty()) {
			throw new ArtesaoException("Não existe Teste de Habilidade com o CPF informado.");
		}

		Boolean testeHabilitado = false;
		for (TesteHabilidade item : list) {
			if (item.getHabilitado()) {
				testeHabilitado = true;
			}
		}
		if (!testeHabilitado) {
			throw new ArtesaoException("O CPF informado, não possui produto habilitado nos testes de habilidade.");
		}

		return repository.save(entidade);
	}

	public void delete(Integer id) {
		Artesao artesao = findById(id);
		repository.delete(artesao);
	}

	private ArtesaoAntigoTipologiaTecnica converterProduto(TesteHabilidade produto) {
		ArtesaoAntigoTipologiaTecnica artesaoAntigoTipologiaTecnica = new ArtesaoAntigoTipologiaTecnica();
		artesaoAntigoTipologiaTecnica.setId(produto.getId());
		artesaoAntigoTipologiaTecnica.setTipologia(produto.getTipologiaTecnica().getTipologia());
		artesaoAntigoTipologiaTecnica.setTecnica(produto.getTipologiaTecnica().getTecnica());
		artesaoAntigoTipologiaTecnica.setMedida(2);
		artesaoAntigoTipologiaTecnica.setProducaoMensal("10");

		return artesaoAntigoTipologiaTecnica;
	}

	private ArtesaoAntigoPessoaFisica converterArtesaoPessoaFisica(Artesao artesao,
			ArtesaoAntigoPessoaFisica artesaoAntigoPessoaFisica) {
		artesaoAntigoPessoaFisica.setId(artesao.getId());
		artesaoAntigoPessoaFisica.setCpf(artesao.getCpf());
		artesaoAntigoPessoaFisica.setComercializacaoConsumidor(true);
		artesaoAntigoPessoaFisica.setEstadoCivil(artesao.getEstadoCivil());
		artesaoAntigoPessoaFisica.setSexo(artesao.getSexo());
		artesaoAntigoPessoaFisica.setDataNascimento(artesao.getDataNascimento());

		return artesaoAntigoPessoaFisica;
	}

	private ArtesaoAntigo converterArtesao(Artesao artesao) {
		ArtesaoAntigo artesaoAntigo = new ArtesaoAntigo();
		artesaoAntigo.setId(artesao.getId());
		artesaoAntigo.setNome(artesao.getNome());
		artesaoAntigo.setEndereco(artesao.getEndereco());
		artesaoAntigo.setMunicipio(artesao.getMunicipio());
		artesaoAntigo.setDataCadastro(artesao.getDataCadastro());
		artesaoAntigo.setSituacao(artesao.getSituacao());
		artesaoAntigo.setNumeroEndereco(artesao.getNumero());
		artesaoAntigo.setTipoArtesao(TipoArtesao.ARTESAO);

		br.com.ivia.ceart.artesao.model.Tecnico tecnico = new br.com.ivia.ceart.artesao.model.Tecnico();
		tecnico.setId(1);

		artesaoAntigo.setTecnico(tecnico);
		artesaoAntigo.setDistrito(artesao.getDistrito());

		if (artesao.getBairro() != null) {
			artesaoAntigo.setBairroId(artesao.getBairro().getId());
		}

		return artesaoAntigo;
	}

	@Transactional
	public HistoricoSituacaoArtesao alterarSituacao(MotivoSituacaoTO to) {

		Artesao artesao = findById(to.getIdEntidade());
		artesao.setSituacao(Situacao.values()[to.getIdSituacao()]);

		HistoricoAcaoArtesao his = new HistoricoAcaoArtesao();
		his.setArtesao(to.getIdEntidade());
		his.setData(new Date());
		his.setRecursoAcao(alterarSituacao);
		his.setUsuario(1);
		historicoAcaoArtesaoRepository.save(his);

		HistoricoSituacaoArtesao hist = new HistoricoSituacaoArtesao();
		hist.setArtesao(to.getIdEntidade());
		hist.setData(new Date());
		MotivoSituacao motivo = new MotivoSituacao();
		motivo.setId(to.getIdMotivo());
		hist.setMotivo(motivo);
		hist.setUsuario(1);
		hist.setObservacao(to.getObservacao());

		return histSitRepository.save(hist);
	}

	@Transactional
	public Artesao renovarCarteira(Artesao artesao) {
		artesao = findById(artesao.getId());

		if (!artesao.getSituacao().equals(Situacao.ATIVADA)) {
			throw new ArtesaoException("O artesão não está ativo, por isso, não é possível renovar a carteira.");
		}

		Integer viaAtual = artesao.getViaCarteira();
		artesao.setViaCarteira(viaAtual + 1);

		Date dataAtual = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dataAtual);
		calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + quantAnosValidadeCarteira);

		artesao.setDataValidadeCarteira(calendar.getTime());
		artesao.setDataAtualizacaoCarteira(new Date());
		repository.save(artesao);

		HistoricoCarteiraArtesao historicoCarteiraArtesao = new HistoricoCarteiraArtesao();
		historicoCarteiraArtesao.setArtesaoId(artesao.getId());
		historicoCarteiraArtesao.setViaCarteira(artesao.getViaCarteira());
		historicoCarteiraArtesao.setDataAtualizacao(new Date());

		historicoCarteiraArtesao.setDataValidade(calendar.getTime());
		historicoCarteiraArtesao.setUsuarioId(1);
		historicoCarteiraArtesaoRepository.save(historicoCarteiraArtesao);
		
		HistoricoAcaoArtesao historicoAcaoArtesao = new HistoricoAcaoArtesao();
		historicoAcaoArtesao.setArtesao(artesao.getId());
		historicoAcaoArtesao.setData(new Date());
		historicoAcaoArtesao.setRecursoAcao(renovacaoCarteira);
		historicoAcaoArtesao.setUsuario(1);
		historicoAcaoArtesaoRepository.save(historicoAcaoArtesao);
		
		return artesao;
	}
	
	@Transactional
	public Artesao imprimirCarteira(Artesao artesao) {
		artesao = findById(artesao.getId());
		
		if(!artesao.getSituacao().equals(Situacao.ATIVADA)) {
			throw new ArtesaoException("O artesão não está ativo, por isso, não é possível imprimir a carteira.");
		}
		
		if(artesao.getDataValidadeCarteira().before(new Date())) {
			throw new ArtesaoException("Não é possível imprimir a carteira, pois está fora da validade.");
		}
				
		HistoricoAcaoArtesao historicoAcaoArtesao = new HistoricoAcaoArtesao();
		historicoAcaoArtesao.setArtesao(artesao.getId());
		historicoAcaoArtesao.setData(new Date());
		historicoAcaoArtesao.setRecursoAcao(impressaoCarteira);
		historicoAcaoArtesao.setUsuario(1);
		historicoAcaoArtesaoRepository.save(historicoAcaoArtesao);
		
		return artesao;
	}	

}