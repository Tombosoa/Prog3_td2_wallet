package com.example.td2wallet.reff;

import com.example.td2wallet.reff.Entity.Account;

import java.util.List;

public class AccountAutoCrud extends Service<Account>{
    public AccountAutoCrud(Class<Account> entityClass) {
        super(entityClass);
    }

    public static void main(String[] args) {
        Service<Account> accountService = Service.createService(Account.class);
        List<Account> accounts = accountService.findAll();

        System.out.println(accounts);
    }
}
