package br.com.famintos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class PessoaNaoEncontradaException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private HttpStatus status;
	
	public PessoaNaoEncontradaException(String message, HttpStatus status) {
		super(message);
		this.status = status;
	}
	
	public PessoaNaoEncontradaException(String message) {
		super(message);
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

}
