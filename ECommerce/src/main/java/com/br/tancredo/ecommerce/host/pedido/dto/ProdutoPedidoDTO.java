package com.br.tancredo.ecommerce.host.pedido.dto;

import javax.validation.constraints.NotNull;

import com.br.tancredo.ecommerce.host.produto.dto.ProdutoDTO;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonRootName(value = "produto")
public class ProdutoPedidoDTO {
	
	@NotNull(message =  "O campo produto é obrigatório.")
	private ProdutoDTO produto;
	
	@NotNull(message = "O campo quantidade é obrigatório.")
	private Integer quantidade;
}
