package com.br.tancredo.ecommerce.host.produto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.tancredo.ecommerce.application.service.ProdutoService;
import com.br.tancredo.ecommerce.host.produto.dto.ProdutoDTO;

@RestController
@RequestMapping("/produtos")
public class ProdutoEndpoint {

	
	private final ProdutoService produtoService;
	
	
	@Autowired
	public ProdutoEndpoint(ProdutoService produtoService) {
		super();
		this.produtoService = produtoService;
	}


	@GetMapping
	public ResponseEntity<List<ProdutoDTO>> listarProdutos() {
		return new ResponseEntity<>(produtoService.listarProdutos(), HttpStatus.OK);
	}



	
	
	
}
