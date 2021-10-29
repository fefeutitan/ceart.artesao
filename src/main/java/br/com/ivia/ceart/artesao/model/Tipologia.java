package br.com.ivia.ceart.artesao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="tipologia",schema="producao")
@JsonInclude(Include.NON_NULL)
@SequenceGenerator(name="seq_tipologia",sequenceName="producao.tipologia_id_seq",allocationSize=1)
@Getter @Setter @NoArgsConstructor @ToString @EqualsAndHashCode(callSuper=false)
public class Tipologia extends BaseModel{

	private static final long serialVersionUID = 3633082141075582100L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq_tipologia")
	@Column(name="id",nullable=false,unique=true)
	private Integer id;
	
	@NotEmpty(message="{campo.nome.obrigatorio}")
	@Column(name="nome",nullable=false,length=100)
	private String nome;
	
	@NotEmpty(message="{campo.sigla.obrigatorio}")
	@Column(name="sigla",nullable=false,length=4)
	private String sigla;
}
