package com.example.td2wallet;

import com.example.td2wallet.Entity.Account;
import com.example.td2wallet.Operation.AccountOperation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.td2wallet")
public class td2wallet {

	public static void main(String[] args) {
		SpringApplication.run(td2wallet.class, args);
	}

}
