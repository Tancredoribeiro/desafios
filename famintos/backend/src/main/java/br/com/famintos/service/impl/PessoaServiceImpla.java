package br.com.famintos.service.impl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.famintos.domain.Pessoa;
import br.com.famintos.dto.PessoaDTO;
import br.com.famintos.dto.PessoaValidaDTO;
import br.com.famintos.exception.PessoaNaoEncontradaException;
import br.com.famintos.repository.PessoaRepository;
import br.com.famintos.service.PessoaService;

@Service
public class PessoaServiceImpla implements PessoaService {

	private PessoaRepository pessoaRepository;

	@Autowired
	public PessoaServiceImpla(PessoaRepository pessoaRepository) {
		this.pessoaRepository = pessoaRepository;
	}

	@Override
	public PessoaDTO validar(PessoaValidaDTO dto) {
		Optional<Pessoa> optional = pessoaRepository.findByUserNameAndSenha(dto.getUserName(), dto.getSenha());
		Pessoa pessoa = optional.orElseThrow(() -> {
			throw new PessoaNaoEncontradaException("Username ou senha inválidos ");
		});
		
		PessoaDTO pessoaDTO = new PessoaDTO();
		 String[]  propriedadesExcluidas = {"senha"};
		BeanUtils.copyProperties(pessoa, pessoaDTO, propriedadesExcluidas);
		return pessoaDTO;
	}

}
