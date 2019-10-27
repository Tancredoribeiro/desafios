package br.com.famintos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class PessoaJaVotouException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private HttpStatus status;

	public PessoaJaVotouException(String message, HttpStatus status) {
		super(message);
		this.status = status;
	}
	
	public PessoaJaVotouException(String message) {
		super(message);
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	
}
