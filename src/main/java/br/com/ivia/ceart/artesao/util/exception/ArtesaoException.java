package br.com.ivia.ceart.artesao.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
@Getter
public class ArtesaoException extends RuntimeException {

	private static final long serialVersionUID = 7820815549826298685L;

	public ArtesaoException(String message) {
		super(message);
	}
}
