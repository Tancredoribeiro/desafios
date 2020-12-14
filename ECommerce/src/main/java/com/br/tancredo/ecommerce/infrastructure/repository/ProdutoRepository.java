package com.br.tancredo.ecommerce.infrastructure.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.tancredo.ecommerce.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, UUID> {

}
