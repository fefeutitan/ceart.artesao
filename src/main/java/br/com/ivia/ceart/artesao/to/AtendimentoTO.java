package br.com.ivia.ceart.artesao.to;

import java.io.Serializable;

import org.springframework.data.domain.Sort.Direction;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AtendimentoTO implements Serializable {

	private static final long serialVersionUID = -8759201466352315732L;
	
	private String protocolo;
	private Integer pageNumber;
	private Integer pageSize;
	private Direction direction;
	private String[] by;
}
