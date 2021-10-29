package br.com.ivia.ceart.artesao.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.ivia.ceart.artesao.util.enums.EstadoCivil;
import br.com.ivia.ceart.artesao.util.enums.Sexo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "artesaopessoafisica", schema = "public")
@JsonInclude(Include.NON_NULL)
@SequenceGenerator(name = "seq_artesaopessoafisica_idartesao", sequenceName = "public.artesaopessoafisica_idartesao_seq", allocationSize = 1)
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class ArtesaoAntigoPessoaFisica extends BaseModel {

	private static final long serialVersionUID = -8855082149496697688L;

	@Id
	@Column(name = "idartesao", nullable = false, unique = true)
	private Integer id;

	@NotNull(message = "{campo.datanascimento.obrigatorio}")
	@Temporal(TemporalType.DATE)
	@Column(name = "datanascimento")
	private Date dataNascimento;

	@NotNull(message = "{campo.sexo.obrigatorio}")
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "sexo")
	private Sexo sexo;

	@NotNull(message = "{campo.estadocivil.obrigatorio}")
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "estadocivil")
	private EstadoCivil estadoCivil;

	@NotEmpty(message = "{campo.cpf.obrigatorio}")
	@Column(name = "cpf", nullable = false, length = 14, unique = true)
	private String cpf;

	@NotNull(message = "{campo.comercializacaoConsumidor.obrigatorio}")
	@Column(name = "comercializacaoconsumidor")
	private Boolean comercializacaoConsumidor;

	@Generated(GenerationTime.INSERT)
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name = "numerocarteira", insertable = false, updatable = false, columnDefinition = "serial")
	private Integer numeroCarteira;

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "artesaoAntigoPessoaFisica", fetch = FetchType.EAGER, orphanRemoval = true, cascade = {
			CascadeType.ALL, CascadeType.PERSIST, CascadeType.MERGE })
	private List<ArtesaoAntigoTipologiaTecnica> produtos;

}
