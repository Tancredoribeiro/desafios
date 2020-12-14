package com.br.tancredo.ecommerce.host.pedido.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PedidoDTO {

	private String id;

	@JsonProperty("data-cadastro")
	@NotNull(message = "O campo data-cadastro é obrigatório.")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime dataCadastro;

	@NotNull(message = "O campo idCliente é obrigatório.")
	private String idCliente;

	@JsonProperty("status-entrega")
	@NotBlank(message = "O campo data-cadastro é obrigatório.")
	private String statusEntrega;

	List<ProdutoPedidoDTO> produtos;
}
