package br.com.ivia.ceart.artesao.to;

import java.io.Serializable;

import org.springframework.data.domain.Sort.Direction;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EntidadeTO implements Serializable {

	private static final long serialVersionUID = 4518511971715196699L;
	
	private Integer municipioId;
	private Integer pageNumber = 1;
	private Integer pageSize = 100;
	private Direction direction = Direction.ASC;
	private String[] by = {"nome"};
}
