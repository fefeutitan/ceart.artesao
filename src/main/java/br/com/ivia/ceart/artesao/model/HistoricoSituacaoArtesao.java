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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "historico_situacao_artesao", schema = "producao")
@JsonInclude(Include.NON_NULL)
@SequenceGenerator(name = "seq_historico", sequenceName = "producao.historico_situacao_artesao_id_seq", allocationSize = 1)
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class HistoricoSituacaoArtesao {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_historico")
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;

	@Column(name = "id_artesao", nullable = true)
	private Integer artesao;

	@ManyToOne
	@JoinColumn(name = "id_motivo", referencedColumnName = "id")
	private MotivoSituacao motivo;

	@Column(name = "data_atual", nullable = true)
	private Date data;

	@Column(name = "usuario", nullable = true)
	private Integer usuario;

	@Column(name = "observacao", nullable = true)
	private String observacao;
}
