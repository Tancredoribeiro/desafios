package br.com.famintos.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.famintos.domain.Restaurante;
import br.com.famintos.dto.RestauranteDTO;
import br.com.famintos.repository.RestauranteRepository;
import br.com.famintos.service.RestauranteService;
import javassist.NotFoundException;

@Service
public class RestauranteServiceImpl implements RestauranteService {

	private RestauranteRepository restauranteRepository;

	@Autowired
	public RestauranteServiceImpl(RestauranteRepository restauranteRepository) {
		this.restauranteRepository = restauranteRepository;
	}

	@Override
	public RestauranteDTO criar(RestauranteDTO dto) {
		Restaurante restaurante = new Restaurante(dto);
		return new RestauranteDTO(restauranteRepository.save(restaurante));
	}

	@Override
	public List<RestauranteDTO> buscarTodos() {
		Sort sort = Sort.by("nome").ascending();
		List<Restaurante> restaurantes = restauranteRepository.findAll(sort);
		
		return restaurantes.stream().map( r -> new RestauranteDTO(r)).collect(Collectors.toList());
	}

	@Override
	public RestauranteDTO buscarPorId(Long id) throws NotFoundException {
		Optional<Restaurante> restauranteOpitional = restauranteRepository.findById(id);
		Restaurante restauranteLoaded = restauranteOpitional.orElseThrow(() -> new NotFoundException(String.format("Restaurante não encontrado com o id %s.", id)));
		RestauranteDTO dto = new RestauranteDTO();
		BeanUtils.copyProperties(restauranteLoaded, dto);
		return dto;
	}

	@Override
	public void excluirPorId(Long id) {
		Optional<Restaurante> restauranteOpitional = restauranteRepository.findById(id);
		Restaurante restauranteLoaded = restauranteOpitional.orElseThrow(() -> new EntityNotFoundException(String.format("Restaurante não encontrado com o id %s.", id)));
		restauranteRepository.delete(restauranteLoaded);
	}

	@Override
	public RestauranteDTO atualizar(@NotNull Long id, RestauranteDTO dto) {
		Optional<Restaurante> restauranteOpitional = restauranteRepository.findById(id);
		Restaurante restauranteLoaded = restauranteOpitional.orElseThrow(() -> new EntityNotFoundException(String.format("Restaurante não encontrado com o id %s.", id)));
		Restaurante restauranteToSave = new Restaurante(dto);
		BeanUtils.copyProperties(restauranteToSave, restauranteLoaded);
		return new RestauranteDTO(restauranteRepository.save(restauranteLoaded));
	
	}

}
