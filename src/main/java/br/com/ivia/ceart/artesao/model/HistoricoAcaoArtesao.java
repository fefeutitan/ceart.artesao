package br.com.ivia.ceart.artesao.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name="historico_acao_artesao",schema="producao")
@JsonInclude(Include.NON_NULL)
@SequenceGenerator(name="seq_historico_artesao",sequenceName="producao.historico_artesao_id_seq",allocationSize=1)
@Getter @Setter @NoArgsConstructor @ToString @EqualsAndHashCode(callSuper=false)
public class HistoricoAcaoArtesao extends BaseModel {
	
    private static final long serialVersionUID = 4998056949905245620L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_historico_artesao")
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;
    
    @Column(name = "id_artesao", nullable = true)
    private Integer artesao;
    
    @Column(name = "id_recurso_acao", nullable = true)
    private Integer recursoAcao;
    
    @Column(name = "data_atual", nullable = true)
    private Date data;
    
    @Column(name = "usuario", nullable = true)
    private Integer usuario;
    
}