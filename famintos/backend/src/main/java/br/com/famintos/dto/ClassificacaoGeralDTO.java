package br.com.famintos.dto;

import java.io.Serializable;
import java.util.Date;

public class ClassificacaoGeralDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idRestaurante;
	private String title;
	private Long votos;
	private Date date;
	private boolean vencedor;

	public ClassificacaoGeralDTO() {
		super();
	}

	

	public ClassificacaoGeralDTO(Long idRestaurante, String title, Long votos, Date date) {
		this.idRestaurante = idRestaurante;
		this.title = title;
		this.votos = votos;
		this.date = date;
	}



	public Long getIdRestaurante() {
		return idRestaurante;
	}



	public void setIdRestaurante(Long idRestaurante) {
		this.idRestaurante = idRestaurante;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
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
