package br.com.ivia.ceart.artesao.to;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Sort.Direction;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaTO implements Serializable{

	private static final long serialVersionUID = -2026565579761839987L;

	private String cpf;	
	private String nome;	
	private String senha;	
	private Integer perfilId;

	private Integer pageNumber;
	private Integer pageSize;
	private Direction direction;
	private String[] by;
}
