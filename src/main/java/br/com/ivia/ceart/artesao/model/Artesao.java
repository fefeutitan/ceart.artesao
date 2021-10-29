package br.com.ivia.ceart.artesao.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.ivia.ceart.artesao.util.enums.CadastroInss;
import br.com.ivia.ceart.artesao.util.enums.CorRaca;
import br.com.ivia.ceart.artesao.util.enums.DestinoProducao;
import br.com.ivia.ceart.artesao.util.enums.Escolaridade;
import br.com.ivia.ceart.artesao.util.enums.EstadoCivil;
import br.com.ivia.ceart.artesao.util.enums.FormaPagamento;
import br.com.ivia.ceart.artesao.util.enums.HorasProducao;
import br.com.ivia.ceart.artesao.util.enums.LocalProducao;
import br.com.ivia.ceart.artesao.util.enums.LocalVive;
import br.com.ivia.ceart.artesao.util.enums.MaiorCliente;
import br.com.ivia.ceart.artesao.util.enums.MaiorFonteRenda;
import br.com.ivia.ceart.artesao.util.enums.Moradia;
import br.com.ivia.ceart.artesao.util.enums.MotivoIngressoArtesanal;
import br.com.ivia.ceart.artesao.util.enums.MotivoNaoPAB;
import br.com.ivia.ceart.artesao.util.enums.PosicaoFamiliar;
import br.com.ivia.ceart.artesao.util.enums.Renda;
import br.com.ivia.ceart.artesao.util.enums.Sexo;
import br.com.ivia.ceart.artesao.util.enums.SistemaTrabalho;
import br.com.ivia.ceart.artesao.util.enums.Situacao;
import br.com.ivia.ceart.artesao.util.enums.SituacaoEspecial;
import br.com.ivia.ceart.artesao.util.enums.StatusAtividade;
import br.com.ivia.ceart.artesao.util.enums.TipoVenda;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "artesao", schema = "producao")
@JsonInclude(Include.NON_NULL)
@SequenceGenerator(name = "seq_artesao", sequenceName = "producao.artesao_id_seq", allocationSize = 1)
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class Artesao extends BaseModel {

	private static final long serialVersionUID = 2166662340142729543L;

	@Id
	// @GeneratedValue(strategy = GenerationType.SEQUENCE, generator =
	// "seq_artesao")
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "id_atendimento", referencedColumnName = "id", nullable = false)
	private Atendimento atendimento;

	@NotEmpty(message = "{campo.cpf.obrigatorio}")
	@Column(name = "cpf", nullable = false, length = 15, unique = true)
	private String cpf;

	@NotEmpty(message = "{campo.nome.obrigatorio}")
	@Column(name = "nome", nullable = false, length = 100)
	private String nome;

	@NotNull(message = "{campo.dataCadastro.obrigatorio}")
	@Temporal(TemporalType.DATE)
	@Column(name = "data_cadastro", nullable = false)
	private Date dataCadastro;

	@NotNull(message = "{campo.dataNascimento.obrigatorio}")
	@Temporal(TemporalType.DATE)
	@Column(name = "data_nascimento", nullable = false)
	private Date dataNascimento;

	@ManyToOne
	@JoinColumn(name = "naturalidade_id", referencedColumnName = "id", nullable = false)
	private Estado naturalidade;

	@ManyToOne
	@JoinColumn(name = "nacionalidade_id", referencedColumnName = "id", nullable = false)
	private Pais nacionalidade;

	@NotEmpty(message = "{campo.numeroIdentidade.obrigatorio}")
	@Column(name = "numero_identidade", nullable = false, length = 20)
	private String numeroIdentidade;

	@NotEmpty(message = "{campo.orgaoExpedidor.obrigatorio}")
	@Column(name = "orgao_expedidor", nullable = false, length = 5)
	private String orgaoExpedidor;

	@ManyToOne
	@JoinColumn(name = "estado_identidade_id", referencedColumnName = "id", nullable = false)
	private Estado estadoIdentidade;

	@NotNull(message = "{campo.dataExpedicaoRg.obrigatorio}")
	@Temporal(TemporalType.DATE)
	@Column(name = "data_expedicao_rg", nullable = false)
	private Date dataExpedicaoRg;

	@Column(name = "nome_pai", nullable = true, length = 100)
	private String nomePai;

	@NotEmpty(message = "{campo.nomePai.obrigatorio}")
	@Column(name = "nome_mae", nullable = false, length = 100)
	private String nomeMae;

	@NotEmpty(message = "{campo.endereco.obrigatorio}")
	@Column(name = "endereco", nullable = false, length = 100)
	private String endereco;

	@NotNull(message = "{campo.numero.obrigatorio}")
	@Column(name = "endereco_numero", nullable = false)
	private String numero;

	@Column(name = "endereco_complemento", nullable = true, length = 100)
	private String complemento;

	@ManyToOne
	@JoinColumn(name = "bairro_id", referencedColumnName = "id", nullable = true)
	private Bairro bairro;

	@ManyToOne
	@JoinColumn(name = "distrito_id", referencedColumnName = "id", nullable = true)
	private Distrito distrito;

	@NotEmpty(message = "{campo.cep.obrigatorio}")
	@Column(name = "cep", nullable = false, length = 15)
	private String cep;

	@ManyToOne
	@JoinColumn(name = "estado_id", referencedColumnName = "id", nullable = false)
	private Estado estado;

	@Column(name = "telefone_fixo", nullable = true, length = 15)
	private String telefoneFixo;

	@Column(name = "telefone_celular", nullable = true, length = 15)
	private String telefoneCelular;

	@Column(name = "rede_social", nullable = true, length = 50)
	private String redeSocial;

	@Column(name = "email", nullable = true, length = 50)
	private String email;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "local_vive", nullable = false)
	private LocalVive localVive;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "posicao_familiar", nullable = false)
	private PosicaoFamiliar posicaoFamiliar;

	@Column(name = "posicao_familiar_especifica", nullable = true, length = 255)
	private String posicaoFamiliarEspecifica;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "situacao_especial", nullable = true)
	private SituacaoEspecial situacaoEspecial;

	@Column(name = "etnia", nullable = true, length = 150)
	private String etnia;

	@Column(name = "registro_funai", nullable = true, length = 50)
	private String registroFunai;

	@Column(name = "imigrante_br", nullable = true)
	private Boolean imigrante;

	@Column(name = "imigrante_estrangeiro", nullable = true)
	private Boolean imigranteEstrangeiro;

	@ManyToOne
	@JoinColumn(name = "pais_origem_id", referencedColumnName = "id", nullable = true)
	private Pais paisOrigem;

	@ManyToOne
	@JoinColumn(name = "outro_estado_id", referencedColumnName = "id", nullable = true)
	private Estado outroEstado;

	@ManyToOne
	@JoinColumn(name = "outro_municipio_id", referencedColumnName = "id", nullable = true)
	private Municipio outroMunicipio;

	@NotNull(message = "{campo.sexo.obrigatorio}")
	@Column(name = "sexo")
	private Sexo sexo;

	@NotNull(message = "{campo.estadoCivil.obrigatorio}")
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "estado_civil", nullable = false)
	private EstadoCivil estadoCivil;

	@NotNull(message = "{campo.CorRaca.obrigatorio}")
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "cor_raca", nullable = false)
	private CorRaca corRaca;

	@NotNull(message = "{campo.escolaridade.obrigatorio}")
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "escolaridade", nullable = false)
	private Escolaridade escolaridade;

	@NotNull(message = "{campo.moradia.obrigatorio}")
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "moradia", nullable = false)
	private Moradia moradia;

	@Column(name = "moradia_especifica", nullable = true)
	private String moradiaEspecifica;

	@NotNull(message = "{campo.possuiDeficiencia.obrigatorio}")
	@Column(name = "possui_deficiencia")
	private Boolean possuiDeficiencia;

	@Column(name = "deficiencia_especifica", nullable = true)
	private String deficienciaEspecifica;

	@NotNull(message = "{campo.dependentesEconomicos.obrigatorio}")
	@Column(name = "dependentes_economicos")
	private Boolean dependentesEconomicos;

	@Column(name = "qunatidade_dependentes_economicos", nullable = true)
	private Integer qntDependentesEconomicos;

	@NotNull(message = "{campo.rendaIndividual.obrigatorio}")
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "renda_individual", nullable = false)
	private Renda rendaIndividual;

	@NotNull(message = "{campo.rendaFamiliar.obrigatorio}")
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "renda_familiar", nullable = false)
	private Renda rendaFamiliar;

	@NotNull(message = "{campo.maiorFonteRenda.obrigatorio}")
	@Column(name = "maior_fonte_renda")
	private MaiorFonteRenda maiorFonteRenda;

	@Column(name = "renda_principal", nullable = true)
	private String rendaPrincipal;

	@NotNull(message = "{campo.contribuiPrevidencia.obrigatorio}")
	@Column(name = "contribui_previdencia")
	private Boolean contribuiPrevidencia;

	@Column(name = "cadastro_inss", nullable = true)
	private CadastroInss cadastroInss;

	@Column(name = "cadastro_inss_especifico", nullable = true)
	private String cadastroInssEspecifico;

	@NotNull(message = "{campo.artesaoDesde.obrigatorio}")
	@Column(name = "artesao_desde", nullable = false)
	private Integer artesaoDesde;

	@NotNull(message = "{campo.statusAtividade.obrigatorio}")
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "status_atividade", nullable = false)
	private StatusAtividade statusAtividade;

	@NotNull(message = "{campo.motivoIngressoArtesanal.obrigatorio}")
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "motivo_ingresso_artesanal")
	private MotivoIngressoArtesanal motivoIngressoArtesanal;

	@Column(name = "motivo_ingresso_artesanal_especifico", nullable = true)
	private String motivoIngressoArtesanalEspecifico;

	@NotNull(message = "{campo.vinculoGrupo.obrigatorio}")
	@Column(name = "vinculo_grupo")
	private Boolean vinculoGrupo;

	@Column(name = "outros_vinculos", nullable = true)
	private Boolean outrosVinculos;

	@Column(name = "outros_vinculos_nome", nullable = true)
	private String outrosVinculosNome;

	@NotNull(message = "{campo.instrutorTecnica.obrigatorio}")
	@Column(name = "instrutor_tecnica")
	private Boolean instrutorTecnica;

	@Column(name = "tipologia", nullable = true)
	private String tipologia;

	@Column(name = "instrutor_desde", nullable = true)
	private Integer instrutorDesde;

	@Column(name = "instrutor_para", nullable = true)
	private String instrutorPara;

	@NotNull(message = "{campo.localProducao.obrigatorio}")
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "local_producao", nullable = false)
	private LocalProducao localProducao;

	@Column(name = "local_producao_especifico", nullable = true)
	private String localProducaoEspecifico;

	@NotNull(message = "{campo.sistemaTrabalho.obrigatorio}")
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "sistema_trabalho", nullable = false)
	private SistemaTrabalho sistemaTrabalho;

	@Column(name = "sistema_trabalho_especifico", nullable = true)
	private String sistemaTrabalhoEspecifico;

	@NotNull(message = "{campo.horasProducao.obrigatorio}")
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "qnt_horas_producao", nullable = false)
	private HorasProducao horasProducao;

	@NotNull(message = "{campo.maiorCliente.obrigatorio}")
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "maior_cliente", nullable = false)
	private MaiorCliente maiorCliente;

	@Column(name = "maior_cliente_especifico", nullable = true)
	private String maiorClienteEspecifico;

	@NotNull(message = "{campo.destinoProducao.obrigatorio}")
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "destino_producao", nullable = false)
	private DestinoProducao destinoProducao;

	@Column(name = "destino_producao_especifico", nullable = true)
	private String destinoProducaoEspecifico;

	@NotNull(message = "{campo.tipoVenda.obrigatorio}")
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "tipo_venda", nullable = false)
	private TipoVenda tipoVenda;

	@Column(name = "tipo_venda_especifica", nullable = true)
	private String tipoVendaEspecifica;

	@Column(name = "local_venda_especifico", nullable = true)
	private String localVendaEspecifico;

	@Column(name = "estado_venda", nullable = true)
	private String estadoVenda;

	@NotNull(message = "{campo.formaPagamento.obrigatorio}")
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "forma_pagamento", nullable = false)
	private FormaPagamento formaPagamento;

	@Column(name = "forma_pagamento_especifico", nullable = true)
	private String formaPagamentoEspecifico;

	@Column(name = "aquisicao_materia_prima", nullable = true)
	private Boolean aquisicaoMateriaPrima;

	@Column(name = "capacitacao_tecnica", nullable = true)
	private Boolean capacitacaoTecnica;

	@Column(name = "acesso_financiamento", nullable = true)
	private Boolean acessoFinanciamento;

	@Column(name = "equipamentos_trabalho", nullable = true)
	private Boolean equipamentoTrabalho;

	@Column(name = "comercializacao", nullable = true)
	private Boolean comercializacao;

	@Column(name = "formacao_precos", nullable = true)
	private Boolean formacaoPrecos;

	@Column(name = "informacao", nullable = true)
	private Boolean informacao;

	@Column(name = "divulgacao", nullable = true)
	private Boolean divulgacao;

	@Column(name = "embalagem", nullable = true)
	private Boolean embalagem;

	@Column(name = "outro", nullable = true)
	private Boolean outro;

	@Column(name = "outro_especifico", nullable = true)
	private String outroEspecifico;

	@Column(name = "numero_carteira", nullable = true)
	private Integer numeroCarteira;

	@Column(name = "via_carteira", nullable = true)
	private Integer viaCarteira;

	@ManyToOne
	@JoinColumn(name = "id_municipio", referencedColumnName = "id", nullable = false)
	private Municipio municipio;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "situacao", nullable = true)
	private Situacao situacao;

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "artesao", fetch = FetchType.EAGER, orphanRemoval = true, cascade = { CascadeType.ALL,
			CascadeType.PERSIST, CascadeType.MERGE })
	private List<ArtesaoEntidade> entidades;

	@Column(name = "foto", nullable = true, length = 150)
	private String foto;

	@Column(name = "def_auditiva", nullable = true)
	private Boolean defAuditiva;

	@Column(name = "def_fisica", nullable = true)
	private Boolean defFisica;

	@Column(name = "def_intelectual", nullable = true)
	private Boolean defIntelectual;

	@Column(name = "def_multipla", nullable = true)
	private Boolean defMultipla;

	@Column(name = "def_visual", nullable = true)
	private Boolean defVisual;

	@Column(name = "def_outra", nullable = true)
	private Boolean defOutra;

	@Column(name = "loc_loja", nullable = true)
	private Boolean locLoja;

	@Column(name = "loc_outros", nullable = true)
	private Boolean locOutros;

	@Column(name = "loc_oficina", nullable = true)
	private Boolean locOficina;

	@Column(name = "loc_feiras", nullable = true)
	private Boolean locFeiras;

	@Column(name = "loc_domicilio_comprador", nullable = true)
	private Boolean locDomicilioComprador;

	@Column(name = "loc_vias_publicas", nullable = true)
	private Boolean locViasPublicas;

	@Column(name = "loc_e_comerce", nullable = true)
	private Boolean locEComerce;

	@Column(name = "local_venda_especifico_estado", nullable = true)
	private String localVendaEspecificoEstado;

	@Column(name = "numero_pab", nullable = true)
	private String numPAB;

	@Column(name = "data_pab", nullable = true)
	private Date dataPAB;

	@Column(name = "motivo_nao_pab", nullable = true)
	private MotivoNaoPAB motivoNaoPAB;

	@Fetch(FetchMode.SUBSELECT)
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "artesao", fetch = FetchType.EAGER, orphanRemoval = true, cascade = { CascadeType.ALL,
			CascadeType.PERSIST, CascadeType.MERGE })
	private List<ArtesaoEdital> editais;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_validade_carteira", nullable = true)
	private Date dataValidadeCarteira;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_atualizacao_carteira", nullable = true)
	private Date dataAtualizacaoCarteira;

}