package com.br.tancredo.ecommerce.application.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.tancredo.ecommerce.application.service.PedidoService;
import com.br.tancredo.ecommerce.domain.Cliente;
import com.br.tancredo.ecommerce.domain.Pedido;
import com.br.tancredo.ecommerce.domain.Produto;
import com.br.tancredo.ecommerce.domain.ProdutoPedido;
import com.br.tancredo.ecommerce.exceptions.ClienteNaoEncontradoException;
import com.br.tancredo.ecommerce.exceptions.ProdutoNaoEncontradoException;
import com.br.tancredo.ecommerce.host.pedido.dto.PedidoDTO;
import com.br.tancredo.ecommerce.host.pedido.dto.ProdutoPedidoDTO;
import com.br.tancredo.ecommerce.host.produto.dto.ProdutoDTO;
import com.br.tancredo.ecommerce.infrastructure.repository.ClienteRepository;
import com.br.tancredo.ecommerce.infrastructure.repository.PedidoRepository;
import com.br.tancredo.ecommerce.infrastructure.repository.ProdutoRepository;
import com.br.tancredo.ecommerce.infrastructure.utils.UUIDUtils;

@Service
public class PedidoServiceImpl implements PedidoService {

	private final ModelMapper mapper;
	private final PedidoRepository pedidoRepository;
	private final ProdutoRepository produtoRepository;
	private final ClienteRepository clienteRepository;

	@Autowired
	public PedidoServiceImpl(ModelMapper mapper, PedidoRepository pedidoRepository, ProdutoRepository produtoRepository,
			ClienteRepository clienteRepository) {
		super();
		this.mapper = mapper;
		this.pedidoRepository = pedidoRepository;
		this.produtoRepository = produtoRepository;
		this.clienteRepository = clienteRepository;
	}

	@Override
	public List<PedidoDTO> listarPedidos() {

		return pedidoRepository.findAll().stream()
				.map(p -> mapper.typeMap(Pedido.class, PedidoDTO.class)
						.addMappings(mapperInterno -> mapperInterno.map(src -> src.getCliente().getId(), PedidoDTO::setIdCliente))
						.map(p))
				.collect(Collectors.toList());

	}
	

	@Override
	public List<PedidoDTO> listarPedidos(String idCliente) {
		UUID uuid = UUIDUtils.validar(idCliente, "idCliente");
		return pedidoRepository.findByClienteId(uuid).stream()
				.map(p -> mapper.typeMap(Pedido.class, PedidoDTO.class)
						.addMappings(mapperInterno -> mapperInterno.map(src -> src.getCliente().getId(), PedidoDTO::setIdCliente))
						.map(p))
				.collect(Collectors.toList());

	}

	@Transactional
	@Override
	public PedidoDTO inserirPedido(PedidoDTO dto, String idCliente) {
		UUID uuidCliente = UUIDUtils.validar(idCliente, "idCliente");
		Optional<Cliente> clienteOptional = clienteRepository.findById(uuidCliente);
		Cliente cliente = clienteOptional.orElseThrow(() -> new ClienteNaoEncontradoException(
				String.format("Cliente não encontrrado com o idCliente: %s", idCliente)));

		List<ProdutoPedido> produtosPedido = mapearValidarProdutos(dto.getProdutos());

		Pedido pedido = mapper.map(dto, Pedido.class);

		pedido.setProdutos(produtosPedido);
		pedido.setCliente(cliente);

		return mapper.typeMap(Pedido.class, PedidoDTO.class)
				.addMappings(mapperInterno -> mapperInterno.map(src -> src.getCliente().getId(), PedidoDTO::setIdCliente))
				.map(pedidoRepository.save(pedido));
	}

	private List<ProdutoPedido> mapearValidarProdutos(List<ProdutoPedidoDTO> produtosDto) {
		if (produtosDto == null || produtosDto.isEmpty()) {
			throw new ConstraintViolationException("A lista de produtos e obrigatória para o pedido", null);
		}

		List<ProdutoPedido> produtos = new ArrayList<>();
		produtosDto.forEach(produtoPedidoDto -> {

			ProdutoDTO produtoDto = produtoPedidoDto.getProduto();
			UUID uuidProduto = UUIDUtils.validar(produtoDto.getId(), "idProduto");

			if (produtoPedidoDto.getQuantidade() <= 0) {
				throw new ConstraintViolationException(
						String.format("A quantidade do produto de id: %s não pode ser zero", produtoDto.getId()), null);
			}
			Optional<Produto> produtoOptional = produtoRepository.findById(uuidProduto);
			Produto produto = produtoOptional.orElseThrow(() -> new ProdutoNaoEncontradoException(
					String.format("Produto não encontrrado com o id: %s", produtoDto.getId())));
			ProdutoPedido produtoPedido = new ProdutoPedido();
			produtoPedido.setProduto(produto);
			produtoPedido.setQuantidade(produtoPedidoDto.getQuantidade());
			produtos.add(produtoPedido);
		});

		return produtos;
	}

}
