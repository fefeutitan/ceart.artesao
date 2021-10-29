package br.com.ivia.ceart.artesao.to;

import java.io.Serializable;

import org.springframework.data.domain.Sort.Direction;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecursoAcaoTO implements Serializable{

	private static final long serialVersionUID = 260329562978991728L;
	
	private String recursoCodigo;
	private String acaoCodigo;

	private Integer pageNumber = 1;
	private Integer pageSize = 500;
	private Direction direction = Direction.ASC;
	private String[] by = {"id"};
}
