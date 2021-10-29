package br.com.ivia.ceart.artesao.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ivia.ceart.artesao.service.ArtesaoService;
import br.com.ivia.ceart.artesao.service.MotivoSituacaoService;
import br.com.ivia.ceart.artesao.to.ArtesaoTO;
import br.com.ivia.ceart.artesao.to.MessageTO;
import br.com.ivia.ceart.artesao.to.MotivoSituacaoTO;

@RestController
@RequestMapping(path = "motivosituacao")
public class MotivoSituacaoRestController {

	@Autowired
	private MotivoSituacaoService service;
	
	@Autowired
	private ArtesaoService artesaoService;

	@PostMapping(path = "findBySituacao", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageTO> findBySituacao(@RequestBody ArtesaoTO to) {
		return new ResponseEntity<>(
				new MessageTO("Opções de motivos encontrados", true, service.findBySituacao(to.getSituacao())),
				HttpStatus.OK);
	}
	
}
