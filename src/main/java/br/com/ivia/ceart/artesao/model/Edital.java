package br.com.ivia.ceart.artesao.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "edital", schema = "producao")
@JsonInclude(Include.NON_NULL)
@SequenceGenerator(name = "seq_edital", sequenceName = "producao.edital_id_seq", allocationSize = 1)
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class Edital extends BaseModel {

	private static final long serialVersionUID = 1951073037937320790L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_edital")
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;

	@Column(name = "numero_edital", nullable = true)
	private String numEdital;

	@Column(name = "data_inicio_vigencia", nullable = true)
	private Date dataInicio;

	@Column(name = "data_fim_vigencia", nullable = true)
	private Date dataFim;
}
