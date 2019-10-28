package br.com.famintos.service.impl;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.famintos.domain.Pessoa;
import br.com.famintos.dto.PessoaDTO;
import br.com.famintos.dto.PessoaValidaDTO;
import br.com.famintos.exception.PessoaNaoEncontradaException;
import br.com.famintos.repository.PessoaRepository;
import br.com.famintos.service.PessoaService;
import javassist.NotFoundException;

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
		String[] propriedadesExcluidas = { "senha" };
		BeanUtils.copyProperties(pessoa, pessoaDTO, propriedadesExcluidas);
		return pessoaDTO;
	}

	@Override
	public Pessoa buscarPorId(@NotNull Long id) throws NotFoundException {
		return pessoaRepository.findById(id).orElseThrow(() -> new NotFoundException("Pessoa não encontrado."));
	}

	@Override
	public PessoaDTO criar(PessoaDTO dto) {
		Pessoa pessoa = new Pessoa();
		BeanUtils.copyProperties(dto, pessoa);

		BeanUtils.copyProperties(pessoaRepository.save(pessoa), dto);
		return dto;

	}

	@Override
	public PessoaDTO atualizar(@NotNull Long id, PessoaDTO dto) {
		Optional<Pessoa> pessoaOpitional = pessoaRepository.findById(id);
		Pessoa pessoaLoaded = pessoaOpitional.orElseThrow(() -> new EntityNotFoundException(String.format("Pessoa não encontrado com o id %s.", id)));
		Pessoa pessoaToSave = new Pessoa();
		BeanUtils.copyProperties(dto, pessoaToSave);
		BeanUtils.copyProperties(pessoaToSave, pessoaLoaded);
		BeanUtils.copyProperties(pessoaRepository.save(pessoaLoaded), dto);
		return dto;
	}

	@Override
	public void excluir(@NotNull Long id) {
		Optional<Pessoa> pessoaOpitional = pessoaRepository.findById(id);
		Pessoa pessoaLoaded = pessoaOpitional.orElseThrow(() -> new EntityNotFoundException(String.format("Pessoa não encontrado com o id %s.", id)));
		pessoaRepository.delete(pessoaLoaded);
		
	}

}
