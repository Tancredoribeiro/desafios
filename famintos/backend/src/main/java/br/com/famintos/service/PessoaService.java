package br.com.famintos.service;

import java.util.List;

import javax.validation.constraints.NotNull;

import br.com.famintos.domain.Pessoa;
import br.com.famintos.dto.PessoaDTO;
import br.com.famintos.dto.PessoaValidaDTO;
import javassist.NotFoundException;

public interface PessoaService {

	PessoaDTO validar(PessoaValidaDTO dto);

	PessoaDTO buscarPorId(@NotNull Long id) throws NotFoundException;

	PessoaDTO criar(PessoaDTO dto);

	PessoaDTO atualizar(@NotNull Long id, PessoaDTO dto);

	void excluir(@NotNull Long id);

	List<PessoaDTO> buscarTodos();

}
