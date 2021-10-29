package br.com.ivia.ceart.artesao.util.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import br.com.ivia.ceart.artesao.model.Artesao;
import br.com.ivia.ceart.artesao.model.Atendimento;
import br.com.ivia.ceart.artesao.model.Municipio;
import br.com.ivia.ceart.artesao.to.ArtesaoTO;
import br.com.ivia.ceart.artesao.util.enums.Situacao;
import br.com.ivia.ceart.artesao.util.exception.ArtesaoException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ArtesaoSpecification implements Specification<Artesao> {

	private static final long serialVersionUID = 9147356375032842829L;

	private ArtesaoTO criteria;

	@Override
	public Predicate toPredicate(Root<Artesao> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		Path<Atendimento> atendimento = root.get("atendimento");
		Path<String> cpf = root.get("cpf");
		Path<String> nome = root.get("nome");
		Path<Long> numeroCarteira = root.get("numeroCarteira");
		Path<Municipio> municipio = root.get("municipio");
		Path<Situacao> situacao = root.get("situacao");
		Path<String> numPAB = root.get("numPAB");
		final List<Predicate> predicates = new ArrayList<Predicate>();

		if (criteria.getNumeroProtocolo() != null && !criteria.getNumeroProtocolo().trim().isEmpty()) {
			if (!criteria.getNumeroProtocolo().contains("/")) {
				throw new ArtesaoException("O número do protocolo não está no formato correto!");
			}

			String protocoloArray[] = criteria.getNumeroProtocolo().split("/");
			predicates.add(cb.equal(atendimento.get("anoProtocolo"), protocoloArray[0]));
			predicates.add(cb.equal(atendimento.get("numeroProtocolo"), protocoloArray[1]));
		}
		if (criteria.getCpf() != null && !criteria.getCpf().trim().isEmpty()) {
			predicates.add(cb.equal(cpf, criteria.getCpf()));
		}
		if (criteria.getNome() != null && !criteria.getNome().trim().isEmpty()) {
			predicates.add(cb.like(cb.lower(nome), "%" + criteria.getNome().trim().toLowerCase() + "%"));
		}
		if (criteria.getNumeroCarteira() != null) {
			predicates.add(cb.equal(numeroCarteira, criteria.getNumeroCarteira()));
		}
		if (criteria.getMunicipioId() != null) {
			predicates.add(cb.equal(municipio.get("id"), criteria.getMunicipioId()));
		}
		if (criteria.getSituacao() != null) {
			predicates.add(cb.equal(situacao, criteria.getSituacao()));
		}
		if (criteria.getNumPAB() != null) {
			predicates.add(cb.equal(numPAB, criteria.getNumPAB()));
		}
		
		return cb.and(predicates.toArray(new Predicate[predicates.size()]));
	}

}
