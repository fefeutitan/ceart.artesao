package br.com.ivia.ceart.artesao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.ivia.ceart.artesao.model.MotivoSituacao;
import br.com.ivia.ceart.artesao.repository.MotivoSituacaoRepository;
import br.com.ivia.ceart.artesao.to.MotivoSituacaoTO;
import br.com.ivia.ceart.artesao.util.enums.Situacao;
import br.com.ivia.ceart.artesao.util.exception.ArtesaoException;
import br.com.ivia.ceart.artesao.util.specification.MotivoSituacaoSpecification;

@Service
public class MotivoSituacaoService {
	
	@Value("${recurso.artesao}")
	private Integer artesaoId;
	
	@Autowired
	MotivoSituacaoRepository repository;
	
	public List<MotivoSituacao> findBySituacao(Situacao situacao){
		MotivoSituacaoTO to = new MotivoSituacaoTO();
		to.setSituacao(situacao);
		to.setIdRecurso(artesaoId);
		to.setAtivo(true);
		
		MotivoSituacaoSpecification specification = new MotivoSituacaoSpecification(to);
		List<MotivoSituacao> list = repository.findAll(specification);
		if (list == null || list.isEmpty()) {
			throw new ArtesaoException("Erro ao procurar por motivos de alterar situação.");
		}
		
		return list;
	}
}
