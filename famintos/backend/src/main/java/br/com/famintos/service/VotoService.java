package br.com.famintos.service;

import java.util.List;

import br.com.famintos.domain.Voto;
import br.com.famintos.dto.VotoDTO;

public interface VotoService {

	List<Voto> buscarTodos();

	VotoDTO votar(VotoDTO dto);

}
