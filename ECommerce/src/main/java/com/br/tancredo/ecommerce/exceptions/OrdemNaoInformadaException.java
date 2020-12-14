package com.br.tancredo.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class OrdemNaoInformadaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public OrdemNaoInformadaException(String message, Exception ex) {
		super(message, ex);
	}

	public OrdemNaoInformadaException(String message) {
		super(message);
	}

}
