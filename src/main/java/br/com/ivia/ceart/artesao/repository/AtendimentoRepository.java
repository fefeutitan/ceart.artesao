package br.com.ivia.ceart.artesao.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.ivia.ceart.artesao.model.Atendimento;


@Repository
public interface AtendimentoRepository extends PagingAndSortingRepository<Atendimento, Integer>, JpaSpecificationExecutor<Atendimento> {

}
