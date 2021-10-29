package br.com.ivia.ceart.artesao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ivia.ceart.artesao.model.ArtesaoAntigo;
import br.com.ivia.ceart.artesao.repository.ArtesaoAntigoRepository;

@Service
public class ArtesaoAntigoService {

	@Autowired
	private ArtesaoAntigoRepository repository;
	
	public ArtesaoAntigo save(ArtesaoAntigo entidade) {
		return repository.save(entidade);
	}
}
