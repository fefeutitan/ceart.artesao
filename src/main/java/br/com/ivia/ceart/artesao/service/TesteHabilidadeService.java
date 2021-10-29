package br.com.ivia.ceart.artesao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.ivia.ceart.artesao.model.TesteHabilidade;
import br.com.ivia.ceart.artesao.repository.TesteHabilidadeRepository;
import br.com.ivia.ceart.artesao.to.MessageTO;
import br.com.ivia.ceart.artesao.to.TesteHabilidadeTO;
import br.com.ivia.ceart.artesao.util.specification.TesteHabilidadeSpecification;

@Service
public class TesteHabilidadeService {
	
	@Autowired TesteHabilidadeRepository repository;
	
	public List<TesteHabilidade> findAll(TesteHabilidadeTO teste){
		TesteHabilidadeSpecification specification = new TesteHabilidadeSpecification(teste);
		PageRequest pageRequest = PageRequest.of(teste.getPageNumber() - 1, teste.getPageSize(), new Sort(teste.getDirection(), teste.getBy()));
		Page<TesteHabilidade> page = repository.findAll(specification, pageRequest);
		List<TesteHabilidade> testes = page.getContent();
		return testes;
	}
	
	public MessageTO findAllBy(TesteHabilidadeTO teste) {
		TesteHabilidadeSpecification specification = new TesteHabilidadeSpecification(teste);
		PageRequest pageRequest = PageRequest.of(teste.getPageNumber() - 1, teste.getPageSize(), new Sort(teste.getDirection(), teste.getBy()));
		Page<TesteHabilidade> page = repository.findAll(specification, pageRequest);
		List<TesteHabilidade> testes = page.getContent();
		return new MessageTO("Testes encontrados", true, page.getTotalPages(), testes);
	}
}
