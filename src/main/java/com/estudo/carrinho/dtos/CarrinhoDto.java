package com.estudo.carrinho.dtos;

import java.util.ArrayList;
import java.util.List;

import com.estudo.carrinho.entities.Produto;
import com.estudo.carrinho.entities.Status;

public class CarrinhoDto {
	private Long id;
	private Status status;
	private List<Produto> produtos = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
}
