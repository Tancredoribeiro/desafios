package br.com.famintos.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

public class PessoaValidaDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotEmpty
	private String userName;
	
	@NotEmpty
	private String senha;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	
	
}
