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

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="recurso_acao",schema="acesso")
@JsonInclude(Include.NON_NULL)
@SequenceGenerator(name="seq_recurso_acao",sequenceName="acesso.recurso_acao_id_seq",allocationSize=1)
@Getter @Setter @NoArgsConstructor @ToString @EqualsAndHashCode(callSuper=false)
public class RecursoAcao extends BaseModel {

	private static final long serialVersionUID = -9154604134990869753L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq_recurso_acao")
	@Column(name="id",nullable=false,unique=true)
	private Integer id;
	
    @ManyToOne
    @JoinColumn(name="id_recurso",nullable=false)
    private Recurso recurso;
    
    @ManyToOne
    @JoinColumn(name="id_acao",nullable=false)
    private Acao acao;   
}

