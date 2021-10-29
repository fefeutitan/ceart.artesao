package br.com.ivia.ceart.artesao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.ivia.ceart.artesao.model.Entidade;
import br.com.ivia.ceart.artesao.repository.EntidadeRepository;
import br.com.ivia.ceart.artesao.to.EntidadeTO;
import br.com.ivia.ceart.artesao.to.MessageTO;
import br.com.ivia.ceart.artesao.util.specification.EntidadeSpecification;

@Service
public class EntidadeService {

    @Autowired
    private EntidadeRepository repository;
    
    public MessageTO findAll(EntidadeTO entidade){
    	EntidadeSpecification specification = new EntidadeSpecification(entidade);
    	PageRequest pageRequest = PageRequest.of(entidade.getPageNumber() - 1, entidade.getPageSize(), new Sort(entidade.getDirection(),entidade.getBy()));
    	Page<Entidade> page = repository.findAll(specification, pageRequest);
    	List<Entidade> entidadeContent = page.getContent();
    	return new MessageTO("Entidades encontradas",true, page.getTotalPages(),entidadeContent);
    }
}
