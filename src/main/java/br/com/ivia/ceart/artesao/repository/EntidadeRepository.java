package br.com.ivia.ceart.artesao.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.ivia.ceart.artesao.model.Entidade;

@Repository
public interface EntidadeRepository extends PagingAndSortingRepository<Entidade, Integer>, JpaSpecificationExecutor<Entidade> {

}
