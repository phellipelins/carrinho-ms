package com.estudo.carrinho.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estudo.carrinho.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}