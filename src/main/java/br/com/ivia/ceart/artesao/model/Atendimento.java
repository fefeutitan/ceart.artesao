package br.com.ivia.ceart.artesao.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.ivia.ceart.artesao.util.enums.SituacaoAtual;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "atendimento", schema = "producao")
@JsonInclude(Include.NON_NULL)
@SequenceGenerator(name = "seq_atendimento", sequenceName = "producao.atendimento_id_atendimento_seq", allocationSize = 1)
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class Atendimento extends BaseModel {

	private static final long serialVersionUID = 2367130072773110172L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq_atendimento")
	@Column(name="id",nullable=false,unique=true)
	private Integer id;
	
	@Column(name = "cpf_cnpj", nullable = true, length = 18)
	private String cpfCnpj;
	
	@Column(name = "solicitante", nullable = true, length = 50)
	private String solicitante;
	
	@Column(name = "ano_protocolo", nullable = false)
	private Integer anoProtocolo;
	
	@Column(name = "numero_protocolo", nullable = false, length = 150)
	private String numeroProtocolo;	
	
	@ManyToOne
    @JoinColumn(name = "cd_municipio", referencedColumnName = "id")
    private Municipio municipio;
	
	@Column(name = "telefone1", nullable = true, length = 50)
	private String telefone1;

	@Column(name = "telefone2", nullable = true, length = 50)
	private String telefone2;
	
	@Column(name = "situacao", nullable = true)
	private SituacaoAtual situacao;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@Fetch(value = FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "atendimento", fetch = FetchType.EAGER, orphanRemoval = true, cascade = { CascadeType.ALL,CascadeType.PERSIST,CascadeType.MERGE })
    private List<TesteHabilidade> testesHabilidade;
}
