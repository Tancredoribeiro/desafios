package com.br.tancredo.ecommerce.host.pedido;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.tancredo.ecommerce.application.service.PedidoService;
import com.br.tancredo.ecommerce.host.pedido.dto.PedidoDTO;
import com.br.tancredo.ecommerce.host.produto.dto.ProdutoDTO;

@RestController
@RequestMapping("/pedidos")
public class PedidoEndpoint {

	private final PedidoService pedidoService;

	@Autowired
	public PedidoEndpoint(PedidoService pedidoService) {
		super();
		this.pedidoService = pedidoService;
	}

	@GetMapping
	public ResponseEntity<List<PedidoDTO>> listarPedidos() {
		return new ResponseEntity<>(pedidoService.listarPedidos(), HttpStatus.OK);
	}
}
