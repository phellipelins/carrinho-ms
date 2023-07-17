package com.estudo.carrinho.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configuracao {

    @Bean
    ModelMapper getModelMapper() {
		return new ModelMapper();
	}
}
