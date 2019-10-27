package br.com.famintos.dto;

import java.io.Serializable;

import br.com.famintos.domain.Restaurante;

public class ClassificacaoHojeDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private RestauranteDTO restaurante;
	private Long votos;
	private boolean vencedor;

	public ClassificacaoHojeDTO() {
		super();
	}

	public ClassificacaoHojeDTO(RestauranteDTO restaurante, Long votos, boolean vencedor) {
		this.restaurante = restaurante;
		this.votos = votos;
		this.vencedor = vencedor;
	}

	public ClassificacaoHojeDTO(Restaurante restaurante, Long votos) {
		this.restaurante = new RestauranteDTO(restaurante);
		this.votos = votos;
	}

	public RestauranteDTO getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(RestauranteDTO restaurante) {
		this.restaurante = restaurante;
	}

	public Long getVotos() {
		return votos;
	}

	public void setVotos(Long votos) {
		this.votos = votos;
	}

	public boolean isVencedor() {
		return vencedor;
	}

	public void setVencedor(boolean vencedor) {
		this.vencedor = vencedor;
	}

}
