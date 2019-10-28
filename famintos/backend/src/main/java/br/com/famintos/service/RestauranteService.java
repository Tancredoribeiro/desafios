package br.com.famintos.service;

import java.util.List;

import javax.validation.constraints.NotNull;

import br.com.famintos.domain.Restaurante;
import br.com.famintos.dto.RestauranteDTO;
import javassist.NotFoundException;

public interface RestauranteService {

	RestauranteDTO criar(RestauranteDTO dto);

	List<RestauranteDTO> buscarTodos();

	Restaurante buscarPorId(@NotNull Long id) throws NotFoundException;

	void excluirPorId(@NotNull Long id);

	RestauranteDTO atualizar(@NotNull Long id, RestauranteDTO dto);

}
