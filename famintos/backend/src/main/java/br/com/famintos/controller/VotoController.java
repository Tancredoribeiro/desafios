package br.com.famintos.controller;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.famintos.domain.Voto;
import br.com.famintos.dto.ClassificacaoGeralDTO;
import br.com.famintos.dto.ClassificacaoHojeDTO;
import br.com.famintos.dto.PessoaDTO;
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
	
	@GetMapping(value = "/classificacao/hoje" , produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	public List<ClassificacaoHojeDTO> buscarClassificacaoHoje() {
		return votoService.buscarClassificacaoHoje();
	}
	
	@GetMapping(value = "/classificacao/geral" , produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	public List<ClassificacaoGeralDTO> buscarClassificacaoGeral() {
		return votoService.buscarClassificacaoGeral();
	}
	
	@GetMapping(value = "/votantes/{idRestaurante}" , produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	public List<PessoaDTO> buscarClassificacaoHoje(@PathVariable("idRestaurante") @NotNull Long id ) {
		return votoService.buascarPessoasVotantesPorRestauranteId(id);
	}
	
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.CREATED)
	public VotoDTO votar(@RequestBody @Validated VotoDTO dto) {			
		return votoService.votar(dto);
	}
	
	
	

}
