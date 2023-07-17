package com.estudo.carrinho.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.estudo.carrinho.dtos.CarrinhoDto;
import com.estudo.carrinho.entities.Carrinho;
import com.estudo.carrinho.repositories.CarrinhoRepository;

@Service
public class CarrinhoService {

	@Autowired
	private CarrinhoRepository carrinhoRepository;

	@Autowired
	private ModelMapper mapper;

	public Page<CarrinhoDto> findAll(Pageable pagination){
		return carrinhoRepository.findAll(pagination).map(carrin -> mapper.map(carrin, CarrinhoDto.class));
	}
	
	public CarrinhoDto findById(Long id) {
		return mapper.map(carrinhoRepository.getReferenceById(id), CarrinhoDto.class);
	}
	
	public CarrinhoDto save(CarrinhoDto carrinhoDto) {
		var carrinho = mapper.map(carrinhoDto, Carrinho.class);

		carrinho = carrinhoRepository.save(carrinho);
		return mapper.map(carrinho, CarrinhoDto.class);
	}
	
	public CarrinhoDto update(Long id, CarrinhoDto carrinhoDto) {
		var carrinho = mapper.map(carrinhoDto, Carrinho.class);

		carrinho.setId(id);
		carrinhoRepository.save(carrinho);
		return mapper.map(carrinho, CarrinhoDto.class);
	}
	
	public void delete(Long id) {
		carrinhoRepository.deleteById(id);
	}
}
