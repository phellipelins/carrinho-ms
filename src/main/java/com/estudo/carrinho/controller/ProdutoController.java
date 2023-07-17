package com.estudo.carrinho.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.estudo.carrinho.dtos.ProdutoDto;
import com.estudo.carrinho.service.ProdutoService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/produto")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	@Operation(description = "Esta função retorna todos os produtos disponíveis.")
	public ResponseEntity<Page<ProdutoDto>> findAll(@PageableDefault(size = 5) Pageable pagination) {
		return ResponseEntity.ok(produtoService.findAll(pagination));
	}
	
	@GetMapping(
		path = "/{id}",
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<ProdutoDto> findById(@PathVariable Long id) {
		return ResponseEntity.ok(produtoService.findById(id));
	}
	
	@PostMapping(
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<ProdutoDto> save(@RequestBody @Valid ProdutoDto produtoDto, UriComponentsBuilder uriBuilder) {
		var tempProduto = produtoService.save(produtoDto);
		
		URI address = uriBuilder.path("/produto/{id}").buildAndExpand(tempProduto.getId()).toUri();
		
		return ResponseEntity.created(address).body(tempProduto);
	}
	
	@PutMapping(
		path = "/{id}",
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<ProdutoDto> update(@PathVariable Long id, @RequestBody @Valid ProdutoDto produtoDto) {
		return ResponseEntity.ok(produtoService.update(id,  produtoDto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		produtoService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
