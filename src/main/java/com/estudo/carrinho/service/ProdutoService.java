package com.estudo.carrinho.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.estudo.carrinho.dtos.ProdutoDto;
import com.estudo.carrinho.entities.Produto;
import com.estudo.carrinho.repositories.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	public Page<ProdutoDto> findAll(Pageable pagination){
		return produtoRepository.findAll(pagination).map(prod -> mapper.map(prod, ProdutoDto.class));
	}
	
	public ProdutoDto findById(Long id) {
		return mapper.map(produtoRepository.getReferenceById(id), ProdutoDto.class);
	}
	
	public ProdutoDto save(ProdutoDto produtoDto) {
		var produto = mapper.map(produtoDto, Produto.class);

		produto = produtoRepository.save(produto);
		return mapper.map(produto, ProdutoDto.class);
	}
	
	public ProdutoDto update(Long id, ProdutoDto produtoDto) {
		var produto = mapper.map(produtoDto, Produto.class);

		produto.setId(id);
		produtoRepository.save(produto);
		return mapper.map(produto, ProdutoDto.class);
	}
	
	public void delete(Long id) {
		produtoRepository.deleteById(id);
	}
}
