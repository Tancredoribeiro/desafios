package br.com.famintos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.famintos.domain.Voto;
import br.com.famintos.dto.VotoDTO;
import br.com.famintos.service.VotoService;

@RestController
@RequestMapping("/votacoes")
public class VotoController {
	
	private VotoService votoService;

	@Autowired
	public VotoController(VotoService votoService) {
		this.votoService = votoService;
	}
	
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Voto> buscarTodosRestaurantes() {
		return votoService.buscarTodos();
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.CREATED)
	public VotoDTO votar(@RequestBody @Validated VotoDTO dto) {			
		return votoService.votar(dto);
	}
	
	
	

}
