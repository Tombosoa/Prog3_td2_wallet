package com.example.td2wallet.Operation;

import com.example.td2wallet.Entity.Account;
import jakarta.el.PropertyNotFoundException;

import java.util.List;

public class AccountOperation implements CrudOperation<Account>{
    @Override
    public List<Account> getAll() {
        return null;
    }

    @Override
    public Account add(Account toAdd) {
        return null;
    }

    @Override
    public void update(Account toUpdate) {

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
