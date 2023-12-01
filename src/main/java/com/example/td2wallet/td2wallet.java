package com.example.td2wallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.td2wallet")
public class td2wallet {

	public static void main(String[] args) {
		SpringApplication.run(td2wallet.class, args);
	}

}
