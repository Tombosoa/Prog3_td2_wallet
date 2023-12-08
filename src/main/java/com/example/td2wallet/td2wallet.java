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
		SpringApplication.run(td2wallet.class, args)
		AccountOperation accountOperation = new AccountOperation();
		Account account = new Account("BNI","98be1e09-62b6-48b6-a9c3-bb6019a96709",6,"Banque",10000.90);
		Account account1 = new Account("BOA","98be1e09-62b6-48b6-a9c3-bb6019a96709",4,"Banque",2000);
		List<Account> accountList = new ArrayList<>();
		accountList.add(account);
		accountList.add(account1);
		accountOperation.saveAll(accountList);

		;
	}

}
