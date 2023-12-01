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
    public void updateCustomer(Account toUpdate) {

    }

    @Override
    public void deleteCustomer(Account toDelete) {

    }

    @Override
    public Account getOne(Account one) throws PropertyNotFoundException {
        return null;
    }
}
