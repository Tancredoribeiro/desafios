package com.br.tancredo.ecommerce.application.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.br.tancredo.ecommerce.application.service.ClienteService;
import com.br.tancredo.ecommerce.domain.Cliente;
import com.br.tancredo.ecommerce.exceptions.ClienteNaoEncontradoException;
import com.br.tancredo.ecommerce.exceptions.OrdemNaoInformadaException;
import com.br.tancredo.ecommerce.host.cliente.dto.ClienteDTO;
import com.br.tancredo.ecommerce.infrastructure.repository.ClienteRepository;
import com.br.tancredo.ecommerce.infrastructure.utils.UUIDUtils;

@Service
public class ClienteServiceImpl implements ClienteService {

	private final ClienteRepository clienteRepository;
	private final ModelMapper mapper;

	@Autowired
	public ClienteServiceImpl(ClienteRepository clienteRepository, ModelMapper mapper) {
		super();
		this.clienteRepository = clienteRepository;
		this.mapper = mapper;
	}

	@Override
	public List<ClienteDTO> listarClientes(String status, String ordem) throws OrdemNaoInformadaException {
		List<Cliente> clientes;

		if (StringUtils.isBlank(ordem)) {
			throw new OrdemNaoInformadaException("O campo ordem é obrigatorio");
		}

		if (StringUtils.isNotBlank(status)) {
			clientes = clienteRepository.findAll(Example.of(Cliente.builder().status(status).build()), Sort.by(ordem));
			return clientes.stream().map(c -> mapper.map(c, ClienteDTO.class)).collect(Collectors.toList());
		}

		clientes = clienteRepository.findAll(Sort.by(ordem));
		return clientes.stream().map(c -> mapper.map(c, ClienteDTO.class)).collect(Collectors.toList());
	}

	@Override
	public ClienteDTO obterCliente( String idCliente) {
		
		UUID id = UUIDUtils.validar(idCliente, "idCliente");
		
		Optional<Cliente> clienteOptional = clienteRepository.findById(id);
		Cliente cliente = clienteOptional
				.orElseThrow(() -> new ClienteNaoEncontradoException(String.format("Cliente não encontrrado com o idCliente: %s", idCliente)));
		return mapper.map(cliente, ClienteDTO.class);
	}



	@Override
	public ClienteDTO inserirCliente(ClienteDTO dto) {
		Cliente cliente = mapper.map(dto, Cliente.class);
		return mapper.map(clienteRepository.save(cliente), ClienteDTO.class);
	}

	@Override
	public ClienteDTO atualizarCliente(ClienteDTO dto, String idCliente) {
		
		UUID id = UUIDUtils.validar(idCliente, "idCliente");
		Optional<Cliente> clienteOptional = clienteRepository.findById(id);
		Cliente cliente = clienteOptional
				.orElseThrow(() -> new ClienteNaoEncontradoException(String.format("Cliente não encontrrado com o idCliente: %s", idCliente)));
		
		BeanUtils.copyProperties(dto, cliente, "id");
		return mapper.map(clienteRepository.save(cliente), ClienteDTO.class);
	}

}
