package com.br.tancredo.ecommerce.application.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.tancredo.ecommerce.application.service.ProdutoService;
import com.br.tancredo.ecommerce.host.produto.dto.ProdutoDTO;
import com.br.tancredo.ecommerce.infrastructure.repository.ProdutoRepository;

@Service
public class ProdutoServiceImpl implements ProdutoService {

	private final ProdutoRepository produtoRepository;
	private final ModelMapper mapper;
	
	
	@Autowired
	public ProdutoServiceImpl(ProdutoRepository produtoRepository, ModelMapper mapper) {
		super();
		this.produtoRepository = produtoRepository;
		this.mapper = mapper;
	}



	@Override
	public List<ProdutoDTO> listarProdutos() {
		return produtoRepository.findAll().stream().map(p -> mapper.map(p, ProdutoDTO.class)).collect(Collectors.toList());
	}
}
