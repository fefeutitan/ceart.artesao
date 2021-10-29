package br.com.ivia.ceart.artesao.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ivia.ceart.artesao.model.ArtesaoAntigoPessoaFisica;
import br.com.ivia.ceart.artesao.repository.ArtesaoAntigoPessoaFisicaRepository;
import br.com.ivia.ceart.artesao.util.exception.ArtesaoException;

@Service
public class ArtesaoAntigoPessoaFisicaService {

	@Autowired
	private ArtesaoAntigoPessoaFisicaRepository repository;
	
	public ArtesaoAntigoPessoaFisica save(ArtesaoAntigoPessoaFisica entidade) {
		return repository.save(entidade);
	}
	
    public ArtesaoAntigoPessoaFisica findById(Integer id) {
        Optional<ArtesaoAntigoPessoaFisica> artesaoAntigoPessoaFisica = repository.findById(id);
        if (!artesaoAntigoPessoaFisica.isPresent()) {
            throw new ArtesaoException("Não é possível localizar o artesão no sistema atual.");
        }
        return artesaoAntigoPessoaFisica.get();
    }
}
