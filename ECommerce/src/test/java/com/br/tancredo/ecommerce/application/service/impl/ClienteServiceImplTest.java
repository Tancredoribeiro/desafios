package com.br.tancredo.ecommerce.application.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;

import com.br.tancredo.ecommerce.application.service.ClienteService;
import com.br.tancredo.ecommerce.domain.Cliente;
import com.br.tancredo.ecommerce.host.cliente.dto.ClienteDTO;
import com.br.tancredo.ecommerce.infrastructure.repository.ClienteRepository;

 
class ClienteServiceImplTest {

	@Mock
	private ClienteRepository clienteRepository;
	
	@Mock
	ModelMapper mapper;
	
	
	@Mock
	private ClienteService clienteService;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		clienteService = new ClienteServiceImpl(clienteRepository, mapper);
	}

	@Test
	void testar_ListarClientes_Sem_informar_Status() {
		
		builClienteMock();		
		List<Cliente> clientes = Arrays.asList(builClienteMock());
		
		Mockito.when(clienteRepository.findAll(Mockito.any(Sort.class))).thenReturn(clientes);
		
		List<ClienteDTO> clientesDto = clienteService.listarClientes(null, "nome");
		
		assertEquals(1, clientesDto.size());
		
		
	}
	
	@Test
	void testar_ListarClientes_Informando_Status() {
		
		builClienteMock();		
		List<Cliente> clientes = Arrays.asList(builClienteMock());
		
		Mockito.when(clienteRepository.findAll(Mockito.any(Example.class), Mockito.any(Sort.class))).thenReturn(clientes);
		
		List<ClienteDTO> clientesDto = clienteService.listarClientes("Ativo", "nome");
		
		assertEquals(1, clientesDto.size());
		
		
	}

	private Cliente builClienteMock() {
		return Cliente.builder().nome("Fulano de Tall").id(UUID.fromString("c704ed16-149a-44d9-a3c2-26b558f8e18d"))
				.status("Ativo").dataCadastro(LocalDate.of(2020, 12, 10)).build();
	}


}
