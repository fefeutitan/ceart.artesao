package br.com.ivia.ceart.artesao.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Municipio não encontrado.")
@Getter
public class MunicipioException extends RuntimeException {

	private static final long serialVersionUID = -7474974397031684199L;

	public MunicipioException(String message) {
		super(message);
	}
}
