package br.com.ivia.ceart.artesao.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.ivia.ceart.artesao.model.Municipio;

@Repository
public interface MunicipioRepository extends PagingAndSortingRepository<Municipio, Integer>, JpaSpecificationExecutor<Municipio> {
	
}
