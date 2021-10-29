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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="teste_habilidade",schema="producao")
@JsonInclude(Include.NON_NULL)
@SequenceGenerator(name="seq_teste_habilidade",sequenceName="producao.teste_habilidade_id_seq",allocationSize=1)
@Getter @Setter @NoArgsConstructor @ToString @EqualsAndHashCode(callSuper=false)
public class TesteHabilidade extends BaseModel{

	private static final long serialVersionUID = -982379484910561154L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq_teste_habilidade")
	@Column(name="id",nullable=false,unique=true)
	private Integer id;
	
	@NotEmpty(message="{campo.nomeProduto.obrigatorio}")
	@Column(name="nome_produto",nullable=false,length=150)
	private String nomeProduto;
	
    @ManyToOne
    @JoinColumn(name="atendimento_id",nullable=false)
    @JsonIgnore
    private Atendimento atendimento;
    
	@NotEmpty
	@Column(name = "cpf", nullable = false, length = 14)
	private String cpf;

	@NotEmpty
	@Column(name = "nome", nullable = false, length = 100)
	private String nome;
    
    @ManyToOne
    @JoinColumn(name="tipologia_tecnica_id",nullable=false)
    private TipologiaTecnica tipologiaTecnica;
    
	@Column(name = "habilitado", nullable = false)
	private Boolean habilitado;
}
