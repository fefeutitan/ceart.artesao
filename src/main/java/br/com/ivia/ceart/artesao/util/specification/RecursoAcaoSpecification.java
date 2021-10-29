package br.com.ivia.ceart.artesao.util.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import br.com.ivia.ceart.artesao.model.Acao;
import br.com.ivia.ceart.artesao.model.Recurso;
import br.com.ivia.ceart.artesao.model.RecursoAcao;
import br.com.ivia.ceart.artesao.to.RecursoAcaoTO;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RecursoAcaoSpecification implements Specification<RecursoAcao> {

	private static final long serialVersionUID = 2433308834041724527L;
	
	private RecursoAcaoTO criteria;

	@Override
	public Predicate toPredicate(Root<RecursoAcao> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		Path<Recurso> recurso = root.get("recurso");
		Path<Acao> acao = root.get("acao");
				
		final List<Predicate> predicates = new ArrayList<Predicate>();	
		
		
		if (criteria.getRecursoCodigo() != null && !criteria.getRecursoCodigo().trim().isEmpty()) {
			predicates.add(cb.equal(recurso.get("codigo"), criteria.getRecursoCodigo()));
		}
		
		if (criteria.getAcaoCodigo() != null && !criteria.getAcaoCodigo().trim().isEmpty()) {
			predicates.add(cb.equal(acao.get("codigo"), criteria.getAcaoCodigo()));
		}

		return cb.and(predicates.toArray(new Predicate[predicates.size()]));
	} 
}
