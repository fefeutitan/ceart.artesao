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
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="artesaotipologiatecnica",schema="public")
@JsonInclude(Include.NON_NULL)
@SequenceGenerator(name="seq_artesaotipologiatecnica",sequenceName="public.artesaotipologiatecnica_id_seq",allocationSize=1)
@Getter @Setter @NoArgsConstructor @ToString @EqualsAndHashCode(callSuper=false)
public class ArtesaoAntigoTipologiaTecnica extends BaseModel {

	private static final long serialVersionUID = -3794380653653532937L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq_artesaotipologiatecnica")
	@Column(name="id",nullable=false,unique=true)
	private Integer id;
	
    @ManyToOne
    @JoinColumn(name="idartesaopessoafisica",nullable=false)
    @JsonIgnore
    private ArtesaoAntigoPessoaFisica artesaoAntigoPessoaFisica;
    
    @ManyToOne
    @JoinColumn(name="idtipologia",nullable=false)
    private Tipologia tipologia;  
    
    @ManyToOne
    @JoinColumn(name="idtecnica",nullable=false)
    private Tecnica tecnica;  
    
	@NotEmpty(message="{campo.producaomensal.obrigatorio}")
	@Column(name="producaomensal",nullable=false,length=20)
	private String producaoMensal;
	
	@NotNull(message="{campo.medida.obrigatorio}")
	@Column(name = "medida", nullable = false)
	private Integer medida;
}
