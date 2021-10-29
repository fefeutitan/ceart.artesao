package br.com.ivia.ceart.artesao.to;

import java.io.Serializable;
import org.springframework.data.domain.Sort.Direction;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TesteHabilidadeTO implements Serializable {

	private static final long serialVersionUID = -4368961267297248184L;
	
	private String cpf;
	
	private Integer pageNumber = 1;
	private Integer pageSize = 100;
	private Direction direction = Direction.ASC;
	private String[] by = {"nome"};
}
