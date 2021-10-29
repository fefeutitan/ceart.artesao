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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "pessoa_perfil", schema = "producao")
@JsonInclude(Include.NON_NULL)
@SequenceGenerator(name="seq_pessoa_perfil",sequenceName="producao.pessoa_perfil_id_seq",allocationSize=1)
@Getter @Setter @NoArgsConstructor @ToString @EqualsAndHashCode(callSuper=false)
public class PessoaPerfil extends BaseModel {

	private static final long serialVersionUID = 4998056949905245620L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pessoa_perfil")
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;
	
	@ManyToOne
    @JoinColumn(name = "id_pessoa", referencedColumnName = "id")
    @JsonIgnore
	private Pessoa pessoa;
	
	@ManyToOne
    @JoinColumn(name = "id_perfil", referencedColumnName = "id")
	private Perfil perfil;	

	@Temporal(TemporalType.DATE)
	@Column(name ="dt_inicio")
	private Date dtInicio;

	@Temporal(TemporalType.DATE)
	@Column(name ="dt_fim")
	private Date dtFim;	

}
