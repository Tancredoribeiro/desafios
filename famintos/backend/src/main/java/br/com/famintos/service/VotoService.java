package br.com.famintos.service;

import java.util.List;

import javax.validation.constraints.NotNull;

import br.com.famintos.domain.Voto;
import br.com.famintos.dto.ClassificacaoHojeDTO;
import br.com.famintos.dto.PessoaDTO;
import br.com.famintos.dto.VotoDTO;

public interface VotoService {

	List<Voto> buscarTodos();

	VotoDTO votar(VotoDTO dto);

	List<ClassificacaoHojeDTO> buscarClassificacaoHoje();

	List<PessoaDTO> buascarPessoasVotantesPorRestauranteId(@NotNull Long id);

}
