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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.ivia.ceart.artesao.util.enums.Medida;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="tipologia_tecnica",schema="producao")
@JsonInclude(Include.NON_NULL)
@SequenceGenerator(name="seq_tipologia_tecnica",sequenceName="producao.tipologia_tecnica_id_seq",allocationSize=1)
@Getter @Setter @NoArgsConstructor @ToString @EqualsAndHashCode(callSuper=false)
public class TipologiaTecnica extends BaseModel{

	private static final long serialVersionUID = 2777573134330314587L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq_tipologia_tecnica")
	@Column(name="id",nullable=false,unique=true)
	private Integer id;
	
    @ManyToOne
    @JoinColumn(name="idtipologia",nullable=false)
    private Tipologia tipologia;  
    
    @ManyToOne
    @JoinColumn(name="idtecnica",nullable=false)
    private Tecnica tecnica;     
    
	@Column(name="qtde_mes",nullable=true)
    private Integer quantidade;

	@Column(name="unidade_medida",nullable=true)
    private Medida unidade;
    
}
