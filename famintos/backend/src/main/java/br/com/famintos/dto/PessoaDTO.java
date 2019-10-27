package br.com.famintos.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

public class PessoaDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotEmpty
	private String nome;
	
	@NotEmpty
	private String userName;
	
	@NotEmpty
	private String senha;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

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
