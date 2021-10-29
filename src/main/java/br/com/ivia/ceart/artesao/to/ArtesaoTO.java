package br.com.ivia.ceart.artesao.to;

import java.io.Serializable;

import org.springframework.data.domain.Sort.Direction;

import br.com.ivia.ceart.artesao.model.Municipio;
import br.com.ivia.ceart.artesao.util.enums.Situacao;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArtesaoTO implements Serializable{

	private static final long serialVersionUID = -2026565579761839987L;

	private String numeroProtocolo;	
	private String cpf;	
	private String nome;	
	private Long numeroCarteira;	
	private Integer municipioId;	
	private Situacao situacao;	
	private Integer pageNumber;
	private Integer pageSize;
	private Direction direction;
	private String[] by;
	
	private String numPAB;
	private String numEdital;
}
