package br.com.ivia.ceart.artesao.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Enitdade não encontrada.")
@Getter
public class EntidadeException extends RuntimeException {

	private static final long serialVersionUID = -7975787430594325050L;
	
	public EntidadeException(String message) {
		super(message);
	}
}
