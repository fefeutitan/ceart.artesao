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
@Table(name = "acao", schema = "acesso")
@JsonInclude(Include.NON_NULL)
@SequenceGenerator(name = "seq_acao", sequenceName = "acesso.acao_id_seq", allocationSize = 1)
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class Acao extends BaseModel {

	private static final long serialVersionUID = 8124279002700217624L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq_acao")
	@Column(name="id",nullable=false,unique=true)
	private Integer id;
	
	@NotEmpty(message = "{campo.nome.obrigatorio}")
	@Column(name = "nome", nullable = false, length = 100)
	private String nome;
	
	@Column(name="situacao",nullable=false)
	private Boolean situacao;
	
	@NotEmpty(message = "{campo.codigo.obrigatorio}")
	@Column(name = "codigo", nullable = false, length = 50)
	private String codigo;
}

