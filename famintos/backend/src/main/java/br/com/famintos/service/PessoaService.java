package br.com.famintos.service;

import br.com.famintos.dto.PessoaDTO;
import br.com.famintos.dto.PessoaValidaDTO;

public interface PessoaService {

	PessoaDTO validar(PessoaValidaDTO dto);

}
