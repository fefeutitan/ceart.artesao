package br.com.ivia.ceart.artesao.util.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import br.com.ivia.ceart.artesao.model.Perfil;
import br.com.ivia.ceart.artesao.model.PerfilAcesso;
import br.com.ivia.ceart.artesao.model.RecursoAcao;
import br.com.ivia.ceart.artesao.to.PerfilAcessoTO;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PerfilAcessoSpecification implements Specification<PerfilAcesso> {

	private static final long serialVersionUID = -1461180148171857302L;
	
	private PerfilAcessoTO criteria;

	@Override
	public Predicate toPredicate(Root<PerfilAcesso> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		Path<Perfil> perfil = root.get("perfil");
		Path<RecursoAcao> recursoAcao = root.get("recursoAcao");
		
		final List<Predicate> predicates = new ArrayList<Predicate>();
		
		if (criteria.getRecursoAcaoId() != null) {
			predicates.add(cb.equal(recursoAcao.get("id"), criteria.getRecursoAcaoId()));
		}
		
		if (criteria.getCodigoRecurso() != null && !criteria.getCodigoRecurso().trim().isEmpty()) {
			Join joinRecursoAcao = root.join("recursoAcao");
			Join joinRecurso = joinRecursoAcao.join("recurso");
			
			predicates.add(cb.equal(joinRecurso.get("codigo"), criteria.getCodigoRecurso()));
		}
		
		if (criteria.getCodigoAcao() != null && !criteria.getCodigoAcao().trim().isEmpty()) {
			Join joinRecursoAcao = root.join("recursoAcao");
			Join joinAcao = joinRecursoAcao.join("acao");
			
			predicates.add(cb.equal(joinAcao.get("codigo"), criteria.getCodigoAcao()));
		}
		
		if (criteria.getPerfis() != null && criteria.getPerfis().length > 0) {
			predicates.add(perfil.get("id").in(criteria.getPerfis()));
		}

		return cb.and(predicates.toArray(new Predicate[predicates.size()]));
	} 
}
