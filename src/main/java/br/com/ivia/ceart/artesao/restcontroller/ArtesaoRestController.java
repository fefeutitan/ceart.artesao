package br.com.ivia.ceart.artesao.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.ivia.ceart.artesao.model.Artesao;
import br.com.ivia.ceart.artesao.model.ArtesaoAntigoPessoaFisica;
import br.com.ivia.ceart.artesao.service.ArtesaoAntigoPessoaFisicaService;
import br.com.ivia.ceart.artesao.service.ArtesaoService;
import br.com.ivia.ceart.artesao.to.ArtesaoTO;
import br.com.ivia.ceart.artesao.to.MessageTO;
import br.com.ivia.ceart.artesao.to.MotivoSituacaoTO;
import br.com.ivia.ceart.artesao.to.TesteHabilidadeTO;
import br.com.ivia.ceart.artesao.service.AtendimentoService;
import br.com.ivia.ceart.artesao.service.EntidadeService;
import br.com.ivia.ceart.artesao.service.StorageService;
import br.com.ivia.ceart.artesao.service.TesteHabilidadeService;
import br.com.ivia.ceart.artesao.to.AtendimentoTO;
import br.com.ivia.ceart.artesao.to.EntidadeTO;

@RestController
@RequestMapping(path = "artesao")
public class ArtesaoRestController {

	@Autowired
	private ArtesaoService service;

	@Autowired
	private AtendimentoService atendimentoService;

	@Autowired
	private EntidadeService entidadeService;

	@Autowired
	private TesteHabilidadeService testeHabilidadeService;

	@Autowired
	private ArtesaoAntigoPessoaFisicaService artesaoAntigoPessoaFisicaService;
	
	@Autowired
	StorageService storageService;

	@RequestMapping("")
	public String index() {
		return "RestController: Index - Artesão";
	}

	@GetMapping(path = "findAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageTO> findAll() {
		return new ResponseEntity<>(new MessageTO("Artesãos encontrados", true, service.findAll()), HttpStatus.OK);
	}

	@PostMapping(path = "visualizarlistagem", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageTO> visualizarlistagem(@RequestBody ArtesaoTO artesao) {
		return new ResponseEntity<>(service.findAll(artesao), HttpStatus.OK);
	}

	@GetMapping(path = "visualizardetalhes/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageTO> visualizardetalhes(@PathVariable("id") Integer id) {
		return new ResponseEntity<>(new MessageTO("Artesão encontrado", true, service.findById(id)), HttpStatus.OK);
	}
	
	@PostMapping(path = "incluir", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageTO> incluir(@RequestBody Artesao artesao) {
		service.validarIntegracao(artesao);
		
		service.integrar(artesao);
		
		if(artesao.getNumeroCarteira() == null && artesao.getId() != null) {
			ArtesaoAntigoPessoaFisica artesaoAntigoPessoaFisica = artesaoAntigoPessoaFisicaService.findById(artesao.getId());
			if(artesaoAntigoPessoaFisica != null) {
				artesao.setNumeroCarteira(artesaoAntigoPessoaFisica.getNumeroCarteira());
				service.save(artesao);
			}
		}
		
		return new ResponseEntity<>(new MessageTO("Artesao salvo com sucesso.", true, artesao),
				HttpStatus.OK);
	}

	@PutMapping(path = "alterar", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageTO> alterar(@RequestBody Artesao artesao) {
		service.validarIntegracao(artesao);
		
		service.integrar(artesao);
		
		if(artesao.getNumeroCarteira() == null && artesao.getId() != null) {
			ArtesaoAntigoPessoaFisica artesaoAntigoPessoaFisica = artesaoAntigoPessoaFisicaService.findById(artesao.getId());
			if(artesaoAntigoPessoaFisica != null) {
				artesao.setNumeroCarteira(artesaoAntigoPessoaFisica.getNumeroCarteira());
				service.save(artesao);
			}
		}
		
		return new ResponseEntity<>(new MessageTO("Artesao salvo com sucesso.", true, artesao),
				HttpStatus.OK);
	}
	
	@PostMapping(path = "renovarcarteira", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageTO> renovarcarteira(@RequestBody Artesao artesao) {	
		return new ResponseEntity<>(new MessageTO("Renovação realizada com sucesso.", true, service.renovarCarteira(artesao)),
				HttpStatus.OK);
	}
	
	@PostMapping(path = "imprimircarteira", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageTO> imprimircarteira(@RequestBody Artesao artesao) {	
		return new ResponseEntity<>(new MessageTO("Impressão realizada com sucesso.", true, service.imprimirCarteira(artesao)),
				HttpStatus.OK);
	}	
	
	@PostMapping(path = "alterarsituacao", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageTO> alterarsituacao(@RequestBody MotivoSituacaoTO motivoSituacao) {
		return new ResponseEntity<>(new MessageTO("Situação de atedimento alterada com sucesso", true,
				service.alterarSituacao(motivoSituacao)), HttpStatus.OK);
	}

	@DeleteMapping(path = "delete/{id}")
	public ResponseEntity<MessageTO> delete(@PathVariable("id") Integer id) {
		service.delete(id);
		return new ResponseEntity<>(new MessageTO("Exclusão realizada com sucesso", true, id), HttpStatus.OK);
	}

	@PostMapping(path = "getAtendimentoByProtocolo", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageTO> getAtendimentoByProtocolo(@RequestBody AtendimentoTO atendimentoTO) {
		return new ResponseEntity<>(atendimentoService.findByProtocolo(atendimentoTO), HttpStatus.OK);
	}

	@GetMapping(path = "getAtendimentoById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageTO> getAtendimentoById(@PathVariable("id") Integer id) {
		return new ResponseEntity<>(new MessageTO("Atendimento encontrado", true, atendimentoService.findById(id)),
				HttpStatus.OK);
	}

	@PostMapping(path = "getEntidadesByMunicipio", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageTO> getEntidadesByMunicipio(@RequestBody EntidadeTO entidade) {
		return new ResponseEntity<>(entidadeService.findAll(entidade), HttpStatus.OK);
	}

	@PostMapping("/upload")
	public ResponseEntity<MessageTO> handleFileUpload(@RequestParam("uploadingFoto") MultipartFile uploadingFoto,
			@RequestParam("artesaoId") Integer id) {
		try {
			storageService.init();

			String nomeArquivo = System.currentTimeMillis() + "_" + uploadingFoto.getOriginalFilename().replaceAll("\\s+", "");
			storageService.store(uploadingFoto, nomeArquivo);

			Artesao artesao = service.findById(id);
			artesao.setFoto(nomeArquivo);
			service.save(artesao);

			return new ResponseEntity<>(new MessageTO("Upload realizado com sucesso!", true, id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new MessageTO(e.getMessage(), false, id), HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@GetMapping("/imagens/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> getImagem(@PathVariable String filename) {
		Resource file = storageService.loadFile(filename);
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).contentType(MediaType.IMAGE_PNG).body(file);
	}
	
	@PostMapping(path = "testesFindAllBy", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageTO> testesFindAllBy(@RequestBody TesteHabilidadeTO testeHabilidadeTO) {
		return new ResponseEntity<>(testeHabilidadeService.findAllBy(testeHabilidadeTO), HttpStatus.OK);
	}	
}
