package com.example.td2wallet.Operation;

import com.example.td2wallet.Entity.Transaction;
import jakarta.el.PropertyNotFoundException;

import java.util.List;

public class TransactionOperation implements CrudOperation<Transaction> {
    @Override
    public List<Transaction> getAll() {
        return null;
    }

    @Override
    public Transaction add(Transaction toAdd) {
        return null;
    }

    @Override
    public void update(Transaction toUpdate) {

    }

    @Override
    public Transaction delete(Transaction toDelete) {
    return null;
    }

    @Override
    public Transaction getOne(Transaction one) throws PropertyNotFoundException {
        return null;
    }
}
