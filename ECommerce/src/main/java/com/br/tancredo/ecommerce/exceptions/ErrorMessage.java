package com.br.tancredo.ecommerce.exceptions;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorMessage {

	@JsonProperty
	private String mensagem;
	
	@JsonProperty
	private boolean sucesso;
	
	@JsonProperty
	private String descricao;
	
	
	public ErrorMessage() {
		super();
	}


	public ErrorMessage(String mensagem, boolean sucesso, String descricao) {
		super();
		this.mensagem = mensagem;
		this.sucesso = sucesso;
		this.descricao = descricao;
	}


	public String getMensagem() {
		return mensagem;
	}


	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}


	public boolean isSucesso() {
		return sucesso;
	}


	public void setSucesso(boolean sucesso) {
		this.sucesso = sucesso;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	
	
}
