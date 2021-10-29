package br.com.ivia.ceart.artesao.util.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import br.com.ivia.ceart.artesao.model.MotivoSituacao;
import br.com.ivia.ceart.artesao.to.MotivoSituacaoTO;
import br.com.ivia.ceart.artesao.util.enums.Situacao;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MotivoSituacaoSpecification implements Specification<MotivoSituacao> {

	private static final long serialVersionUID = -5245682648262499092L;

	private MotivoSituacaoTO criteria;

	@Override
	public Predicate toPredicate(Root<MotivoSituacao> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		Path<Integer> recurso = root.get("recursoId");
		Path<Situacao> situacao = root.get("situacao");
		Path<Boolean> ativo = root.get("ativo");

		List<Predicate> predicates = new ArrayList<>();

		if (criteria.getIdRecurso() != null) {
			predicates.add(cb.equal(recurso, criteria.getIdRecurso()));
		}
		if (criteria.getSituacao() != null) {
			predicates.add(cb.equal(situacao, criteria.getSituacao()));
		}
		if (criteria.getAtivo() != null) {
			predicates.add(cb.equal(ativo, criteria.getAtivo()));
		}

		return cb.and(predicates.toArray(new Predicate[predicates.size()]));
	}

}
