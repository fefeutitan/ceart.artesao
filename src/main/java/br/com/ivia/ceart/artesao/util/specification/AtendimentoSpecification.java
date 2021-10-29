package br.com.ivia.ceart.artesao.util.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import br.com.ivia.ceart.artesao.model.Atendimento;
import br.com.ivia.ceart.artesao.to.AtendimentoTO;
import br.com.ivia.ceart.artesao.util.exception.ArtesaoException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AtendimentoSpecification implements Specification<Atendimento> {

	private static final long serialVersionUID = -2005280637120914930L;
	private AtendimentoTO criteria;
	
	@Override
	public Predicate toPredicate(Root<Atendimento> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		Path<Integer> anoProtocolo = root.get("anoProtocolo");
		Path<String> numeroProtocolo = root.get("numeroProtocolo");
		
		final List<Predicate> predicates = new ArrayList<Predicate>();
		
		if (criteria.getProtocolo() != null) {
			if(!criteria.getProtocolo().contains("/")) {
				throw new ArtesaoException("O número do protocolo não está no formato correto!");
			}
			
			String protocolo[] = criteria.getProtocolo().split("/");
			predicates.add(cb.equal(anoProtocolo, protocolo[0]));
			predicates.add(cb.equal(numeroProtocolo, protocolo[1]));
		}
		
		return cb.and(predicates.toArray(new Predicate[predicates.size()]));
	}
}
