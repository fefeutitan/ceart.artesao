package br.com.ivia.ceart.artesao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ivia.ceart.artesao.model.RecursoAcao;
import br.com.ivia.ceart.artesao.repository.RecursoAcaoRepository;
import br.com.ivia.ceart.artesao.to.RecursoAcaoTO;
import br.com.ivia.ceart.artesao.util.specification.RecursoAcaoSpecification;


@Service
public class RecursoAcaoService {

	@Autowired
	private RecursoAcaoRepository repository;
	
	public List<RecursoAcao> findAllBy(RecursoAcaoTO recursoAcaoTO) {
		RecursoAcaoSpecification specification = new RecursoAcaoSpecification(recursoAcaoTO);
		return repository.findAll(specification);
	}
}
