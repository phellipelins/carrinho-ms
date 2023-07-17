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

import com.estudo.carrinho.dtos.CarrinhoDto;
import com.estudo.carrinho.entities.Status;
import com.estudo.carrinho.service.CarrinhoService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/carrinho")
public class CarrinhoController {

	@Autowired
	private CarrinhoService carrinhoService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	@Operation(description = "Esta função retorna todos os carrinhos de compra.")
	public ResponseEntity<Page<CarrinhoDto>> findAll(@PageableDefault(size = 5) Pageable pagination) {
		return ResponseEntity.ok(carrinhoService.findAll(pagination));
	}
	
	@GetMapping(
		path = "/{id}",
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<CarrinhoDto> findById(@PathVariable Long id) {
		return ResponseEntity.ok(carrinhoService.findById(id));
	}
	
	@PostMapping(
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<CarrinhoDto> save(@RequestBody @Valid CarrinhoDto carrinhoDto, UriComponentsBuilder uriBuilder) {
		var tempCarrinho = carrinhoService.save(carrinhoDto);
		
		URI address = uriBuilder.path("/carrinho/{id}").buildAndExpand(tempCarrinho.getId()).toUri();
		
		return ResponseEntity.created(address).body(tempCarrinho);
	}
	
	@PutMapping(
		path = "/{id}",
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<CarrinhoDto> update(@PathVariable Long id, @RequestBody @Valid CarrinhoDto carrinhoDto) {
		return ResponseEntity.ok(carrinhoService.update(id,  carrinhoDto));
	}
	
	@PutMapping(path = "/{id}/confirmar")
	public ResponseEntity<CarrinhoDto> confirmar(@PathVariable Long id) {
		CarrinhoDto carrinhoDto = carrinhoService.findById(id);
		
		carrinhoDto.setStatus(Status.CONFIRMADO);
		
		return ResponseEntity.ok(carrinhoService.update(id,  carrinhoDto));
	}

	@PutMapping(path = "/{id}/cancelar")
	public ResponseEntity<CarrinhoDto> cancelar(@PathVariable Long id) {
		CarrinhoDto carrinhoDto = carrinhoService.findById(id);
		
		carrinhoDto.setStatus(Status.CANCELADO);
		return ResponseEntity.ok(carrinhoService.update(id,  carrinhoDto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		carrinhoService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
