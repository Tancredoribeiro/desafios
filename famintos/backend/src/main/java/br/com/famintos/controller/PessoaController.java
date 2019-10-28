package br.com.famintos.controller;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.famintos.domain.Pessoa;
import br.com.famintos.dto.PessoaDTO;
import br.com.famintos.dto.PessoaValidaDTO;
import br.com.famintos.service.PessoaService;
import javassist.NotFoundException;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

	private PessoaService pessoaService;

	@Autowired
	public PessoaController(PessoaService pessoaService) {
		this.pessoaService = pessoaService;
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	public Pessoa getRestaurante(@PathVariable("id") @NotNull Long id) throws NotFoundException {
		return pessoaService.buscarPorId(id);
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.CREATED)
	public PessoaDTO criar(@RequestBody @Validated PessoaDTO dto) {		
		return pessoaService.criar(dto);
	}
	
	@PutMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	public PessoaDTO update(@PathVariable("id")  @NotNull Long id, @RequestBody @Validated PessoaDTO dto) {
		return pessoaService.atualizar(id, dto);
	}

	@DeleteMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public ResponseEntity<Object> excluir(@PathVariable("id") @NotNull Long id) {
		pessoaService.excluir(id);
		return ResponseEntity.noContent().build();
	}

	@PostMapping(value = "/validar", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	public PessoaDTO validar(@RequestBody @Validated PessoaValidaDTO dto) {
		return pessoaService.validar(dto);
	}

}
