package com.br.tancredo.ecommerce.host.cliente;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.br.tancredo.ecommerce.application.service.ClienteService;
import com.br.tancredo.ecommerce.application.service.PedidoService;
import com.br.tancredo.ecommerce.exceptions.OrdemNaoInformadaException;
import com.br.tancredo.ecommerce.host.cliente.dto.ClienteDTO;
import com.br.tancredo.ecommerce.host.pedido.dto.PedidoDTO;

@RestController
@RequestMapping("/clientes")
public class ClienteEndpoint {

	private final ClienteService clienteService;
	final PedidoService pedidoService;

	@Autowired
	public ClienteEndpoint(ClienteService clienteService, PedidoService pedidoService) {
		super();
		this.clienteService = clienteService;
		this.pedidoService = pedidoService;
	}

	@GetMapping
	public ResponseEntity<List<ClienteDTO>> listarClientes(
			@RequestParam(name = "status", required = false) String status,
			@RequestParam(name = "ordem", required = true) String ordem) throws OrdemNaoInformadaException {
		return new ResponseEntity<>(clienteService.listarClientes(status, ordem), HttpStatus.OK);
	}

	@GetMapping(value = "/{idCliente}")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<ClienteDTO> obterCliente(
			@PathVariable(name = "idCliente", required = true) String idCliente) {
		return new ResponseEntity<>(clienteService.obterCliente(idCliente), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{idCliente}/pedidos")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<List<PedidoDTO>> listarPedidos(
			@PathVariable(name = "idCliente", required = true) String idCliente) {
		return new ResponseEntity<>(pedidoService.listarPedidos(idCliente), HttpStatus.OK);
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<ClienteDTO> inserirCliente(@RequestBody @Valid ClienteDTO dto) {
		return new ResponseEntity<>(clienteService.inserirCliente(dto), HttpStatus.CREATED);
	}

	@PutMapping(value = "/{idCliente}")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<ClienteDTO> atualizarCliente(@RequestBody @Valid ClienteDTO dto,
			@PathVariable(name = "idCliente", required = true) @NotBlank String idCliente) {
		return new ResponseEntity<>(clienteService.atualizarCliente(dto, idCliente), HttpStatus.OK);
	}

	@PostMapping(value = "/{idCliente}/pedidos")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<PedidoDTO> inserirPedido(@RequestBody @Valid PedidoDTO dto, @PathVariable(value = "idCliente", required = true)String idCliente) {
		return new ResponseEntity<>(pedidoService.inserirPedido(dto, idCliente), HttpStatus.OK);
	}

}
