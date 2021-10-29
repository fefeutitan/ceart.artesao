package br.com.ivia.ceart.artesao.util.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import br.com.ivia.ceart.artesao.model.Entidade;
import br.com.ivia.ceart.artesao.model.Municipio;
import br.com.ivia.ceart.artesao.to.EntidadeTO;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EntidadeSpecification implements Specification<Entidade> {

	private static final long serialVersionUID = -1343902132341090162L;
	
	private EntidadeTO criteria;
	
	@Override
	public Predicate toPredicate(Root<Entidade> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		Path<Municipio> municipio = root.get("municipio");
		final List<Predicate> predicates = new ArrayList<Predicate>();
	
		if (criteria.getMunicipioId() != null) {
			predicates.add(cb.equal(municipio.get("id"), criteria.getMunicipioId()));
		}	

		return cb.and(predicates.toArray(new Predicate[predicates.size()]));
	}
}
