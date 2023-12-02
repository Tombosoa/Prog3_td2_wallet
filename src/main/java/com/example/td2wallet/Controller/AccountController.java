package com.example.td2wallet.Controller;

import com.example.td2wallet.Entity.Account;
import com.example.td2wallet.Operation.AccountOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class AccountController {
    private final AccountOperation accountOperation;

    @Autowired
    public AccountController(AccountOperation accountOperation) {
        this.accountOperation = accountOperation;
    }

    @GetMapping("/accounts")
    public List<Account> getAccounts(){
        return accountOperation.findAll();
    }
}
