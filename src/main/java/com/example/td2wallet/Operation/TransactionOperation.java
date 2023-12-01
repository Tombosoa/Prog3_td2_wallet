package com.example.td2wallet.Operation;

import com.example.td2wallet.Entity.Transaction;
import jakarta.el.PropertyNotFoundException;

import java.util.List;

public class TransactionOperation implements CrudOperation<Transaction> {
    @Override
    public List<Transaction> findAll() {
        return null;
    }

    @Override
    public List<Transaction> saveAll(List<Transaction> toSave) {
        return null;
    }

    @Override
    public Transaction save(Transaction toAdd) {
        return null;
    }

    @Override
    public Transaction update(Transaction toUpdate) {
return null;
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
