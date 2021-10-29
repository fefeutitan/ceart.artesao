package br.com.ivia.ceart.artesao.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ivia.ceart.artesao.service.EditalService;
import br.com.ivia.ceart.artesao.to.ArtesaoTO;
import br.com.ivia.ceart.artesao.to.MessageTO;

@RestController
@RequestMapping(path = "edital")
public class EditalRestController {

	@Autowired
	EditalService service;

	@PostMapping(path = "findByNumber")
	public ResponseEntity<MessageTO> findByNumber(@RequestBody ArtesaoTO to) {
		return new ResponseEntity<>(new MessageTO("Edital Encontrado", true, service.findByNumber(to.getNumEdital())),
				HttpStatus.OK);
	}
}
