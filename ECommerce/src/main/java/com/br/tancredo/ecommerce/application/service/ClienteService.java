package com.br.tancredo.ecommerce.application.service;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import com.br.tancredo.ecommerce.exceptions.OrdemNaoInformadaException;
import com.br.tancredo.ecommerce.host.cliente.dto.ClienteDTO;

public interface  ClienteService {
	
	List<ClienteDTO> listarClientes(String status, String ordem) throws OrdemNaoInformadaException;
	
	ClienteDTO inserirCliente(ClienteDTO dto);

	ClienteDTO obterCliente(String idCliente);

	ClienteDTO atualizarCliente(ClienteDTO dto, String idCliente);
}
