package br.com.ivia.ceart.artesao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.ivia.ceart.artesao.util.enums.Situacao;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "motivo_situacao", schema = "producao")
@JsonInclude(Include.NON_NULL)
@SequenceGenerator(name = "seq_motivo", sequenceName = "producao.motivo_situacao_id_seq", allocationSize = 1)
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class MotivoSituacao extends BaseModel {

	private static final long serialVersionUID = -7188439587693678396L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_motivo")
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;
	
	@Column(name = "id_recurso", nullable = false)
	private Integer recursoId;

	@Column(name = "situacao", nullable = false)
	private Situacao situacao;

	@Column(name = "descricao", nullable = false)
	private String descricao;

	@Column(name = "ativo", nullable = false)
	private Boolean ativo;
}