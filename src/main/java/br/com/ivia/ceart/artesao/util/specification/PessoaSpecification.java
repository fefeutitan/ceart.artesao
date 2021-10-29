package br.com.ivia.ceart.artesao.util.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import br.com.ivia.ceart.artesao.model.Pessoa;
import br.com.ivia.ceart.artesao.to.PessoaTO;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PessoaSpecification implements Specification<Pessoa> {

	private static final long serialVersionUID = 9147356375032842829L;

	private PessoaTO criteria;

	@Override
	public Predicate toPredicate(Root<Pessoa> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		Path<String> cpf = root.get("cpf");
		Path<String> nome = root.get("nome");
				
		final List<Predicate> predicates = new ArrayList<Predicate>();	
		
		if (criteria.getCpf() != null && !criteria.getCpf().trim().isEmpty()) {
			predicates.add(cb.equal(cpf, criteria.getCpf()));
		}
		if (criteria.getNome() != null && !criteria.getNome().trim().isEmpty()) {
			predicates.add(cb.like(cb.lower(nome), "%" + criteria.getNome().trim().toLowerCase() + "%"));
		}

		return cb.and(predicates.toArray(new Predicate[predicates.size()]));
	} 

}
