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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="artesao_entidade",schema="producao")
@JsonInclude(Include.NON_NULL)
@SequenceGenerator(name="seq_artesao_entidade",sequenceName="producao.artesao_entidade_id_seq",allocationSize=1)
@Getter @Setter @NoArgsConstructor @ToString @EqualsAndHashCode(callSuper=false)
public class ArtesaoEntidade extends BaseModel {

	private static final long serialVersionUID = 3923829804097542927L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq_artesao_entidade")
	@Column(name="id",nullable=false,unique=true)
	private Integer id;
	
    @ManyToOne
    @JoinColumn(name="id_artesao",nullable=false)
    @JsonIgnore
    private Artesao artesao;
    
    @ManyToOne
    @JoinColumn(name="id_entidade",nullable=false)
    private Entidade entidade;    
}
