package com.aguang.jinjuback;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.aguang.jinjuback.dao")
@SpringBootApplication
public class JinjuBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(JinjuBackApplication.class, args);
	}
}
