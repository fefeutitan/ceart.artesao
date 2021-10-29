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
@Table(name="pais",schema="corporativo")
@JsonInclude(Include.NON_NULL)
@SequenceGenerator(name="seq_pais",sequenceName="corporativo.pais_id_seq",allocationSize=1)
@Getter @Setter @NoArgsConstructor @ToString @EqualsAndHashCode(callSuper=false)
public class Pais extends BaseModel{

	private static final long serialVersionUID = -6447651606043545458L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq_pais")
	@Column(name="id",nullable=false,unique=true)
	private Integer id;
	
	@NotEmpty(message="{campo.nome.obrigatorio}")
	@Column(name="nome",nullable=false,length=50)
	private String nome;
}
