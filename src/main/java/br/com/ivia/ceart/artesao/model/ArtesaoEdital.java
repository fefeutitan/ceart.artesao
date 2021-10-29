package br.com.ivia.ceart.artesao.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "artesao_edital", schema = "producao")
@JsonInclude(Include.NON_NULL)
@SequenceGenerator(name = "seq_artesao_edital", sequenceName = "producao.artesao_edital_id_seq", allocationSize = 1)
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class ArtesaoEdital extends BaseModel {

	private static final long serialVersionUID = -2058638754278580795L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_artesao_edital")
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;

	@Column(name = "data_atualizacao", nullable = true)
	private Date data;
	
	@ManyToOne
	@JoinColumn(name = "id_artesao", referencedColumnName = "id")
	@JsonIgnore
	private Artesao artesao;

	@ManyToOne
	@JoinColumn(name = "id_edital", referencedColumnName = "id")
	private Edital edital;
}
