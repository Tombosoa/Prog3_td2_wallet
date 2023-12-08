package com.example.td2wallet;

import com.example.td2wallet.Entity.Account;
import com.example.td2wallet.Entity.Devise;
import com.example.td2wallet.Entity.Transaction;
import com.example.td2wallet.Operation.AccountOperation;
import com.example.td2wallet.Operation.DeviseOperation;
import com.example.td2wallet.Operation.TransactionOperation;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class Prog4ApplicationTests {

	@Test
	void contextLoads() {
	}

	public static void main(String[] args) {
		AccountOperation accountOperation = new AccountOperation();
		TransactionOperation transactionOperation = new TransactionOperation();
		DeviseOperation deviseOperation = new DeviseOperation();
		//get all account
		accountOperation.findAll();
		//get all transaction
		transactionOperation.findAll();
		//get all devise
		deviseOperation.findAll();

//create list of account
		List<Account> accountList = new ArrayList<>();
		Account account = new Account("BOA","d4bfd8d9-bb39-427b-9977-b92d0b3e8db0",1,"Banque",2000);
		accountList.add(account);
		accountOperation.saveAll(accountList);
		//create list of devise
		List<Devise> deviseList = new ArrayList<>();
		Devise devise = new Devise("Euro","EUR");
		deviseList.add(devise);
		deviseOperation.saveAll(deviseList);
	}

}
