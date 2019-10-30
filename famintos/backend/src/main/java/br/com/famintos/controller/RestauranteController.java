package br.com.famintos.controller;

import java.util.List;

import javax.validation.constraints.NotNull;

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

import br.com.famintos.dto.RestauranteDTO;
import br.com.famintos.service.RestauranteService;
import javassist.NotFoundException;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

	private RestauranteService restauranteService;

	public RestauranteController(RestauranteService restauranteService) {
		this.restauranteService = restauranteService;
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<RestauranteDTO> buscarTodosRestaurantes() {
		return restauranteService.buscarTodos();
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	public RestauranteDTO getRestaurante(@PathVariable("id") @NotNull Long id) throws NotFoundException {
		return restauranteService.buscarPorId(id);
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.CREATED)
	public RestauranteDTO criar(@RequestBody @Validated RestauranteDTO dto) {		
		return restauranteService.criar(dto);
	}
	
	@PutMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	public RestauranteDTO atualizar(@PathVariable("id")  @NotNull Long id, @RequestBody @Validated RestauranteDTO dto) {
		return restauranteService.atualizar(id, dto);
	}

	@DeleteMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public ResponseEntity<Object> excluir(@PathVariable("id") @NotNull Long id) {
		restauranteService.excluirPorId(id);
		return ResponseEntity.noContent().build();
	}

}
