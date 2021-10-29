package br.com.ivia.ceart.artesao.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ivia.ceart.artesao.model.Municipio;
import br.com.ivia.ceart.artesao.repository.MunicipioRepository;
import br.com.ivia.ceart.artesao.util.exception.MunicipioException;

@Service
public class MunicipioService {
	@Autowired
	private MunicipioRepository repository;

	public Municipio findById(Integer id) {
		Optional<Municipio> municipio = repository.findById(id);
		if (!municipio.isPresent()) {
			throw new MunicipioException("Erro ao procurar o Municipio");
		}
		return municipio.get();
	}
}
