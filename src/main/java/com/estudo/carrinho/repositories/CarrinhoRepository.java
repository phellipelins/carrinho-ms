package com.estudo.carrinho.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estudo.carrinho.entities.Carrinho;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Long>{

}
