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
@Table(name="perfil_acesso",schema="acesso")
@JsonInclude(Include.NON_NULL)
@SequenceGenerator(name="seq_perfil_acesso",sequenceName="acesso.perfil_acesso_id_seq",allocationSize=1)
@Getter @Setter @NoArgsConstructor @ToString @EqualsAndHashCode(callSuper=false)
public class PerfilAcesso extends BaseModel {

	private static final long serialVersionUID = -7331788266680677694L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq_perfil_acesso")
	@Column(name="id",nullable=false,unique=true)
	private Integer id;
	
    @ManyToOne
    @JoinColumn(name="id_perfil",nullable=false)
    private Perfil perfil;
    
    @ManyToOne
    @JoinColumn(name="id_recurso_acao",nullable=false)
    private RecursoAcao recursoAcao;
    
	@Column(name="acesso",nullable=false)
	private Boolean acesso;

}
