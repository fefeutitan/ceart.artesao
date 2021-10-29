package br.com.ivia.ceart.artesao.to;

import java.io.Serializable;

import org.springframework.data.domain.Sort.Direction;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PerfilAcessoTO implements Serializable{

	private static final long serialVersionUID = -6114530356790292499L;

	private Integer recursoAcaoId;
	
	private Integer perfilId;
	private Integer recursoId;
	private Integer acaoId;
	private Integer[] perfis;
	
	private String codigoRecurso;
	private String codigoAcao;
	
	private Integer pageNumber;
	private Integer pageSize;
	private Direction direction;
	private String[] by;
}
