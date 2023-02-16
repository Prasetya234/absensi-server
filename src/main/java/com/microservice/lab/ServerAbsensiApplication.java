package com.microservice.lab;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;

@EnableJdbcAuditing
@SpringBootApplication
public class ServerAbsensiApplication {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	public static void main(String[] args) {
		SpringApplication.run(ServerAbsensiApplication.class, args);
	}

}
