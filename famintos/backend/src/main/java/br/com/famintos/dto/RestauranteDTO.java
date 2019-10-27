package br.com.famintos.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import br.com.famintos.domain.Restaurante;

public class RestauranteDTO  implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	@NotEmpty
	private String nome;

	@NotEmpty
	private String endereco;
	
	

	public RestauranteDTO() {
		super();
	}

	public RestauranteDTO(Restaurante restaurante) {
		this.id = restaurante.getId();
		this.nome = restaurante.getNome();
		this.endereco = restaurante.getEndereco();
	}

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

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

}
