package com.br.tancredo.ecommerce.application.service;

import java.util.List;

import com.br.tancredo.ecommerce.host.pedido.dto.PedidoDTO;

public interface PedidoService {

	List<PedidoDTO> listarPedidos();

	PedidoDTO inserirPedido(PedidoDTO dto, String idCliente);

	List<PedidoDTO> listarPedidos(String idCliente);

}
