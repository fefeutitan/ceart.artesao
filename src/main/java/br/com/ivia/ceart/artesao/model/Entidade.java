package br.com.ivia.ceart.artesao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "entidade", schema = "producao")
@JsonInclude(Include.NON_NULL)
@SequenceGenerator(name = "seq_entidade", sequenceName = "producao.entidade_id_seq", allocationSize = 1)
@Getter @Setter @NoArgsConstructor @ToString @EqualsAndHashCode(callSuper = false)
public class Entidade extends BaseModel {

	private static final long serialVersionUID = -6525822822217938318L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_entidade")
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;
	
	@NotEmpty(message = "{campo.nome.obrigatorio}")
	@Column(name = "nome", nullable = false, length = 100)
	private String nome;
	
    @ManyToOne
    @JoinColumn(name = "id_municipio", referencedColumnName = "id", nullable = false)
    private Municipio municipio;
}
