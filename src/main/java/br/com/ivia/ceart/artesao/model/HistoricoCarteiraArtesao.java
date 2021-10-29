package br.com.ivia.ceart.artesao.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="historico_carteira_artesao",schema="producao")
@JsonInclude(Include.NON_NULL)
@SequenceGenerator(name="seq_historico_carteira_artesao",sequenceName="producao.historico_carteira_artesao_id_seq",allocationSize=1)
@Getter @Setter @NoArgsConstructor @ToString @EqualsAndHashCode(callSuper=false)
public class HistoricoCarteiraArtesao extends BaseModel {

	private static final long serialVersionUID = 4827132817903235005L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq_historico_carteira_artesao")
	@Column(name="id",nullable=false,unique=true)
	private Integer id;
	
    @Column(name = "id_artesao", nullable = true)
    private Integer artesaoId;
    
	@Column(name = "via_carteira", nullable = false)
	private Integer viaCarteira;
	
	@NotNull(message = "{campo.dataAtualizacao.obrigatorio}")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_atualizacao", nullable = false)
	private Date dataAtualizacao;
	
	@NotNull(message = "{campo.dataAtualizacao.obrigatorio}")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_validade", nullable = false)
	private Date dataValidade;	
	
	@Column(name = "id_usuario", nullable = true)
	private Integer usuarioId;	
}
