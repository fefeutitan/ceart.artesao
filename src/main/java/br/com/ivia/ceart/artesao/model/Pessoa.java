package br.com.ivia.ceart.artesao.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.ivia.ceart.artesao.util.enums.SituacaoPessoa;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "pessoa", schema = "producao")
@JsonInclude(Include.NON_NULL)
@SequenceGenerator(name = "seq_pessoa", sequenceName = "producao.pessoa_id_pessoa_seq", allocationSize = 1)
@Getter @Setter @NoArgsConstructor @ToString @EqualsAndHashCode(callSuper=false)
public class Pessoa extends BaseModel {

	private static final long serialVersionUID = 4998056949905245620L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pessoa")
	@Column(name = "id", nullable = false, unique = true)
	@JoinColumn()
	private Integer id;

	@Column(name = "nome", nullable = true, length = 50)
	private String nome;
	
	@Column(name = "email", nullable = true, length = 50)
	private String email;

	@Column(name = "telefone1", nullable = true, length = 50)
	private String telefone;

	@Column(name = "senha", nullable = true, length = 50)
	private String senha;
	
	@Column(name = "cpf", nullable = true, length = 14)
	private String cpf;
	
	@Enumerated(EnumType.ORDINAL)
    @Column(name = "situacao_id", nullable = true)
    private SituacaoPessoa situacaoPessoa;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@Fetch(FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "pessoa", fetch = FetchType.EAGER, orphanRemoval = true, cascade = { CascadeType.ALL,CascadeType.PERSIST,CascadeType.MERGE })
    private List<PessoaPerfil> perfis;
}
