package br.com.ivia.ceart.artesao.util.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import br.com.ivia.ceart.artesao.model.TesteHabilidade;
import br.com.ivia.ceart.artesao.to.TesteHabilidadeTO;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TesteHabilidadeSpecification implements Specification<TesteHabilidade> {

	private static final long serialVersionUID = -5412042791358180136L;
	
	private TesteHabilidadeTO criteria;
	
	@Override
	public Predicate toPredicate(Root<TesteHabilidade> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		Path<String> cpf = root.get("cpf");
		final List<Predicate> predicates = new ArrayList<Predicate>();
		
		if (criteria.getCpf() != null && !criteria.getCpf().trim().isEmpty()) {
			predicates.add(cb.equal(cpf, criteria.getCpf()));
		}
		
		return cb.and(predicates.toArray(new Predicate[predicates.size()]));
	}
}
