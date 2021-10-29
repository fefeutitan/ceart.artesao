package br.com.ivia.ceart.artesao.util.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import br.com.ivia.ceart.artesao.model.Edital;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EditalSpecification implements Specification<Edital> {

	private static final long serialVersionUID = 351880175634542510L;

	private String criteria;

	@Override
	public Predicate toPredicate(Root<Edital> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		Path<String> number = root.get("numEdital");
		final List<Predicate> predicates = new ArrayList<Predicate>();

		if (number != null) {
			predicates.add(cb.equal(number, criteria));
		}

		return cb.and(predicates.toArray(new Predicate[predicates.size()]));
	}

}
