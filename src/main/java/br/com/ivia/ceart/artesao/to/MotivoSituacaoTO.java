package br.com.ivia.ceart.artesao.to;

import java.io.Serializable;

import br.com.ivia.ceart.artesao.util.enums.Situacao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MotivoSituacaoTO implements Serializable {

	private static final long serialVersionUID = -1896918488957161572L;

	private Integer idEntidade;
	private Integer idMotivo;
	private Integer idSituacao;
	private Integer usuario;
	private String observacao;
	
	private Situacao situacao;
	private Integer idRecurso;
	private Boolean ativo;
}
