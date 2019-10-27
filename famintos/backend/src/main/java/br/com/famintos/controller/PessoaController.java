package br.com.famintos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.famintos.dto.PessoaDTO;
import br.com.famintos.dto.PessoaValidaDTO;
import br.com.famintos.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

	private PessoaService pessoaService;

	@Autowired
	public PessoaController(PessoaService pessoaService) {
		this.pessoaService = pessoaService;
	}

	@PostMapping(value = "/validar", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	public PessoaDTO validar(@RequestBody @Validated PessoaValidaDTO dto) {
		return pessoaService.validar(dto);
	}

}
