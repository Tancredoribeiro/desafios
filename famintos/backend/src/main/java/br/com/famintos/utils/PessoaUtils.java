package br.com.famintos.utils;

import org.springframework.beans.BeanUtils;

import br.com.famintos.domain.Pessoa;
import br.com.famintos.dto.PessoaDTO;

public class PessoaUtils {

	public static PessoaDTO montarPessoaDTO(Pessoa p) {
		PessoaDTO dto = new PessoaDTO();
		String[] propriadesdesEsxluir = {"senha"};
		BeanUtils.copyProperties(p, dto, propriadesdesEsxluir);
		return dto;
	}
}
