package com.example.td2wallet.Operation;

import com.example.td2wallet.Entity.Account;
import jakarta.el.PropertyNotFoundException;

import java.util.List;

public class AccountOperation implements CrudOperation<Account>{
    @Override
    public List<Account> findAll() {
        return null;
    }

    @Override
    public List<Account> saveAll(List<Account> toSave) {
        return null;
    }

    @Override
    public Account save(Account toAdd) {
        return null;
    }

    @Override
    public Account update(Account toUpdate) {
return null;
    }

    @Override
    public Account delete(Account toDelete) {
        return null;
    }

    @Override
    public Account getOne(Account one) throws PropertyNotFoundException {
        return null;
    }
}
