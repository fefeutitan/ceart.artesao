package br.com.ivia.ceart.artesao.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.ivia.ceart.artesao.util.enums.Situacao;
import br.com.ivia.ceart.artesao.util.enums.TipoArtesao;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "artesao", schema = "public")
@JsonInclude(Include.NON_NULL)
@SequenceGenerator(name = "seq_artesao_antigo", sequenceName = "public.artesao_idartesao_seq", allocationSize = 1)
@Getter @Setter @NoArgsConstructor @ToString @EqualsAndHashCode(callSuper = false)
public class ArtesaoAntigo extends BaseModel {

	private static final long serialVersionUID = 1604351044575122544L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_artesao_antigo")
	@Column(name = "idartesao", nullable = false, unique = true)
	private Integer id;
	
	@NotEmpty(message = "{campo.nome.obrigatorio}")
	@Column(name = "nome", nullable = false, length = 100)
	private String nome;
	
	@NotEmpty(message = "{campo.endereco.obrigatorio}")
	@Column(name = "endereco", nullable = false, length = 100)
	private String endereco;
	
    @NotEmpty
    @Column(name = "numeroendereco", length = 10)
    private String numeroEndereco;

    @Column(name = "complemento", length = 100)
    private String complemento;
	
    @ManyToOne
    @JoinColumn(name = "idmunicipio", referencedColumnName = "id", nullable = false)
    private Municipio municipio; 
    
    @Column(name = "nomebairro", length = 50)
    private String nomeBairro;

    @Column(name = "nomedistrito", length = 50)
    private String nomeDistrito;
    
    @Column(name = "cep", length = 9)
    private String cep;

    @Column(name = "telefone", length = 20)
    private String telefone;

    @Column(name = "numeroconta", length = 10)
    private String numeroConta;
    
    @NotNull
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "situacao", nullable = false)
    private Situacao situacao;
    
    @NotNull
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "tipoartesao", nullable = false)
    private TipoArtesao tipoArtesao;
    
	@NotNull(message = "{campo.dataCadastro.obrigatorio}")
	@Temporal(TemporalType.DATE)
	@Column(name = "datacadastro", nullable = false)
	private Date dataCadastro;
	
    @ManyToOne
    @JoinColumn(name = "idtecnico", referencedColumnName = "id", nullable = false)
    private Tecnico tecnico; 
    
    @ManyToOne
    @JoinColumn(name = "iddistrito", referencedColumnName = "id", nullable = true)
    private Distrito distrito; 
    
    @Column(name = "idbairro", nullable = true)
    private Integer bairroId; 
}
