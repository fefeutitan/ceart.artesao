package br.com.ivia.ceart.artesao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.ivia.ceart.artesao.model.Artesao;
import br.com.ivia.ceart.artesao.model.ArtesaoEntidade;
import br.com.ivia.ceart.artesao.model.Atendimento;
import br.com.ivia.ceart.artesao.model.Distrito;
import br.com.ivia.ceart.artesao.model.Entidade;
import br.com.ivia.ceart.artesao.model.Estado;
import br.com.ivia.ceart.artesao.model.Municipio;
import br.com.ivia.ceart.artesao.model.Pais;
import br.com.ivia.ceart.artesao.restcontroller.ArtesaoRestController;
import br.com.ivia.ceart.artesao.service.ArtesaoService;
import br.com.ivia.ceart.artesao.service.AtendimentoService;
import br.com.ivia.ceart.artesao.service.EntidadeService;
import br.com.ivia.ceart.artesao.to.ArtesaoTO;
import br.com.ivia.ceart.artesao.to.EntidadeTO;
import br.com.ivia.ceart.artesao.to.MessageTO;
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
import br.com.ivia.ceart.artesao.util.enums.PosicaoFamiliar;
import br.com.ivia.ceart.artesao.util.enums.Renda;
import br.com.ivia.ceart.artesao.util.enums.Sexo;
import br.com.ivia.ceart.artesao.util.enums.SistemaTrabalho;
import br.com.ivia.ceart.artesao.util.enums.Situacao;
import br.com.ivia.ceart.artesao.util.enums.StatusAtividade;
import br.com.ivia.ceart.artesao.util.enums.TipoVenda;

import static org.mockito.Mockito.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ArtesaoRestControllerTests extends ApplicationTests {

	private MockMvc mockMvc;

	@InjectMocks
	private ArtesaoRestController artesaoRestController;

	@Mock
	private ArtesaoService artesaoServiceMock;

	@Mock
	private AtendimentoService atendimentoServiceMock;
	
	@Mock
	private EntidadeService entidadeServiceMock;
	
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(artesaoRestController).build();
	}
	
	@Test
	public void index() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/artesao")).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test

	public void testFindById() throws Exception {
	    Artesao artesao = new Artesao();
	    artesao.setId(1);
	    artesao.setNome("Maria do Socorro");
	    
	    when(artesaoServiceMock.findById(1)).thenReturn(artesao);
	    
	    this.mockMvc.perform(MockMvcRequestBuilders.get("/artesao/visualizardetalhes/" + 1))
	            .andExpect(MockMvcResultMatchers.status().isOk())
	            .andExpect(MockMvcResultMatchers.jsonPath("$.response.id").value(1))
	            .andExpect(MockMvcResultMatchers.jsonPath("$.response.nome").value("Maria do Socorro"));
	    
	    verify(artesaoServiceMock, times(1)).findById(1);
	    verifyNoMoreInteractions(artesaoServiceMock);
	}
	
	@Test
	public void testeGetAtendimentoById() throws Exception {	
	    Atendimento atendimento = new Atendimento();
	    atendimento.setId(1);
	    atendimento.setAnoProtocolo(2018);
	    atendimento.setNumeroProtocolo("123456");
	    
	    when(atendimentoServiceMock.findById(1)).thenReturn(atendimento);
	    
	    this.mockMvc.perform(MockMvcRequestBuilders.get("/artesao/getAtendimentoById/" + 1))
	            .andExpect(MockMvcResultMatchers.status().isOk())
	            .andExpect(MockMvcResultMatchers.jsonPath("$.response.id").value(1))
	            .andExpect(MockMvcResultMatchers.jsonPath("$.response.anoProtocolo").value(2018))
	            .andExpect(MockMvcResultMatchers.jsonPath("$.response.numeroProtocolo").value("123456"));
	    
	    verify(atendimentoServiceMock, times(1)).findById(1);
	    verifyNoMoreInteractions(atendimentoServiceMock);		
	}

	@Test
	public void testFindAll() throws Exception {
		List<Artesao> artesaos = new ArrayList<Artesao>();
		
	    Artesao artesao1 = new Artesao();
	    artesao1.setId(1);
	    artesao1.setNome("Maria do Socorro");
	    
	    Artesao artesao2 = new Artesao();
	    artesao2.setId(2);
	    artesao2.setNome("Francisco Chagas");
	    
	    artesaos.add(artesao1);
	    artesaos.add(artesao2);
		
	    when(artesaoServiceMock.findAll()).thenReturn(artesaos);

		this.mockMvc
		.perform(MockMvcRequestBuilders.get("/artesao/findAll"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
		.andExpect(MockMvcResultMatchers.jsonPath("$.response[0].nome").value("Maria do Socorro"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.response[1].nome").value("Francisco Chagas"));

		
		 verify(artesaoServiceMock, times(1)).findAll();
		 verifyNoMoreInteractions(artesaoServiceMock);
	}
	
	@Test
	public void testFindAllBy() throws Exception {
		List<Artesao> artesaos = new ArrayList<Artesao>();

		
	    Artesao artesao1 = new Artesao();
	    artesao1.setId(1);
	    artesao1.setNome("Maria do Socorro");
	    
	    Artesao artesao2 = new Artesao();
	    artesao2.setId(2);
	    artesao2.setNome("Francisco Chagas");
	    
	    artesaos.add(artesao1);
	    artesaos.add(artesao2);
	    
	    MessageTO messageTO = new MessageTO();
	    messageTO.setMessage("Artesãos encontrados");
	    messageTO.setNumberOfPages(1);
	    messageTO.setSuccess(true);
	    messageTO.setResponse(artesaos);
	    
		ArtesaoTO artesaoTO = new ArtesaoTO();
		artesaoTO.setNome("Francisco Chagas");
		artesaoTO.setPageNumber(1);
		artesaoTO.setDirection(Direction.DESC);
		artesaoTO.setPageSize(1);
		String[] by = { "nome" };
		artesaoTO.setBy(by);
		
	    when(artesaoServiceMock.findAll(artesaoTO)).thenReturn(messageTO);
		
		this.mockMvc.perform(MockMvcRequestBuilders.post("/artesao/visualizarlistagem").contentType(MediaType.APPLICATION_JSON)
						.content(asJsonString(artesaoTO)))
				.andExpect(MockMvcResultMatchers.status().isOk());
				//.andExpect(MockMvcResultMatchers.jsonPath("$.response[0].nome").value("Francisco Chagas"));
	}
	

	@Test
	public void testInsert() throws Exception {
		Artesao artesao = objetoMock();
			
		when(artesaoServiceMock.save(artesao)).thenReturn(artesao);
	   
		this.mockMvc.perform(MockMvcRequestBuilders.post("/artesao/incluir").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(artesao)))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true));
			    
	}

	private Artesao objetoMock() {
		Artesao artesao = new Artesao();
		artesao.setCpf("005.217.590-13");
		artesao.setNome("Fulano Silva 123");
		artesao.setDataCadastro(new Date());
		artesao.setDataNascimento(new Date());
		
		artesao.setFormaPagamento(FormaPagamento.CARTAO);
		artesao.setTipoVenda(TipoVenda.CONSIGNACAO);
		artesao.setDestinoProducao(DestinoProducao.INTERNACIONAL);
		artesao.setMaiorCliente(MaiorCliente.CONSUMIDOR);
		artesao.setHorasProducao(HorasProducao.ATE2);
		artesao.setSistemaTrabalho(SistemaTrabalho.ARTESAOS);
		artesao.setLocalProducao(LocalProducao.ASSOCIACAO);
		artesao.setInstrutorTecnica(true);
		artesao.setVinculoGrupo(false);
		artesao.setMotivoIngressoArtesanal(MotivoIngressoArtesanal.COMPLEMENTO);
		artesao.setStatusAtividade(StatusAtividade.PRINCIPAL);
		artesao.setArtesaoDesde(2000);
		artesao.setContribuiPrevidencia(true);
		artesao.setMaiorFonteRenda(MaiorFonteRenda.ATIVIDADEARTESANAL);
		artesao.setRendaFamiliar(Renda.ACIMADE10);
		artesao.setRendaIndividual(Renda.ACIMADE10);
		artesao.setDependentesEconomicos(false);
		artesao.setPossuiDeficiencia(false);
		artesao.setMoradia(Moradia.ALBERGUE);
		artesao.setEscolaridade(Escolaridade.FUNDAMENTAL1AO5);
		artesao.setCorRaca(CorRaca.BRANCO);
		artesao.setEstadoCivil(EstadoCivil.CASADO);
		artesao.setSexo(Sexo.MASCULINO);
		artesao.setPosicaoFamiliar(PosicaoFamiliar.CHEFE);
		artesao.setLocalVive(LocalVive.ZONARURAL);
		artesao.setCep("60356-140");
		
		Estado estado = new Estado();
		estado.setId(1);
		
		artesao.setEstado(estado);
		artesao.setNumero("33");
		artesao.setEndereco("kfsjdkf sd");
		artesao.setNomeMae("fksj dkjfsljf l");
		artesao.setDataExpedicaoRg(new Date());
		artesao.setEstadoIdentidade(estado);
		artesao.setOrgaoExpedidor("ddd");
		artesao.setNumeroIdentidade("535435");
		artesao.setSituacao(Situacao.ATIVADA);

		Pais pais = new Pais();
		pais.setId(2);
		
		artesao.setNacionalidade(pais);
		artesao.setNaturalidade(estado);
		
		Atendimento atendimento = new Atendimento();
		atendimento.setId(130);
		
		artesao.setAtendimento(atendimento);
		
		Entidade entidade = new Entidade();
		entidade.setId(2);
		
		ArtesaoEntidade artesaoEntidade = new ArtesaoEntidade();
		artesaoEntidade.setEntidade(entidade);
		
		List<ArtesaoEntidade> list = new ArrayList<ArtesaoEntidade>();
		list.add(artesaoEntidade);
		
		artesao.setEntidades(list);
		
		Municipio municipio = new Municipio();
		municipio.setId(59);
		
		artesao.setMunicipio(municipio);
		
		Distrito distrito = new Distrito();
		distrito.setId(1);
		
		artesao.setDistrito(distrito);
		
		return artesao;
	}


	/*
	@Test
	public void testUpdate() throws Exception {
	    Artesao artesao = new Artesao();
	    artesao.setId(1);
	    artesao.setNome("Maria do Socorro");

	    when(artesaoServiceMock.save(artesao)).thenReturn(artesao);

		this.mockMvc
				.perform(MockMvcRequestBuilders.post("/artesao/save").contentType(MediaType.APPLICATION_JSON)
						.content(asJsonString(artesao)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true));
	}
	*/


	@Test
	public void testDelete() throws Exception {
	    Artesao artesao = new Artesao();
	    artesao.setId(1);
	    artesao.setNome("Maria do Socorro");

	    when(artesaoServiceMock.findById(artesao.getId())).thenReturn(artesao);
        doNothing().when(artesaoServiceMock).delete(artesao.getId());
		
		this.mockMvc.perform(MockMvcRequestBuilders.delete("/artesao/delete/" + artesao.getId()))
				.andExpect(MockMvcResultMatchers.status().isOk());
		
	}
	
	@Test
	public void testGetEntidadesByMunicipioFilter() throws Exception {
		List<Entidade> entidades = new ArrayList<Entidade>();
		
		Entidade entidade1 = new Entidade();
		entidade1.setId(1);
		
		Entidade entidade2 = new Entidade();
		entidade2.setId(2);
		
		entidades.add(entidade1);
		entidades.add(entidade2);
		
	    MessageTO messageTO = new MessageTO();
	    messageTO.setMessage("Entidades encontradas");
	    messageTO.setNumberOfPages(1);
	    messageTO.setSuccess(true);
	    messageTO.setResponse(entidades);
	    
		EntidadeTO entidade = new EntidadeTO();
		entidade.setMunicipioId(1);
		entidade.setPageNumber(1);
		entidade.setDirection(Direction.ASC);
		entidade.setPageSize(1);
		String[] by = { "nome" };
		entidade.setBy(by);

		when(entidadeServiceMock.findAll(entidade)).thenReturn(messageTO);

		this.mockMvc
				.perform(MockMvcRequestBuilders.post("/artesao/getEntidadesByMunicipio").contentType(MediaType.APPLICATION_JSON)
						.content(asJsonString(entidade)))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
