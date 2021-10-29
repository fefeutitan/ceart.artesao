package br.com.ivia.ceart.artesao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ivia.ceart.artesao.model.Edital;
import br.com.ivia.ceart.artesao.repository.EditalRepository;
import br.com.ivia.ceart.artesao.util.exception.ArtesaoException;
import br.com.ivia.ceart.artesao.util.specification.EditalSpecification;

@Service
public class EditalService {
	
	@Autowired
	private EditalRepository repository;
	
	public Edital findByNumber(String number){
		EditalSpecification specification = new EditalSpecification(number);
		if(!repository.findOne(specification).isPresent()){
			throw new ArtesaoException("Edital não existente ou não encontrado.");
		}
		return repository.findOne(specification).get();
	}
}
