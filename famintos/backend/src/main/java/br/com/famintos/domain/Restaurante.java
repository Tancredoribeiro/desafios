package br.com.famintos.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.famintos.dto.RestauranteDTO;

@Entity
@Table(name = "restaurante")
public class Restaurante {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome")
	private String nome;

	@Column(name = "endereco")
	private String endereco;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "restaurante_id")
	private Set<Voto> votos;

	public Restaurante() {
		super();
	}

	public Restaurante(RestauranteDTO dto) {
		this.id = dto.getId();
		this.nome = dto.getNome();
		this.endereco = dto.getEndereco();
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

	public Set<Voto> getVotos() {
		return votos;
	}

	public void setVotos(Set<Voto> votos) {
		this.votos = votos;
	}

}
