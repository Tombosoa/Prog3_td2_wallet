package com.example.td2wallet.Operation;

import com.example.td2wallet.Entity.Devise;
import jakarta.el.PropertyNotFoundException;

import java.util.List;

public class DeviseOperation implements CrudOperation<Devise> {
    @Override
    public List<Devise> getAll() {
        return null;
    }

    @Override
    public Devise add(Devise toAdd) {
        return null;
    }

    @Override
    public void updateCustomer(Devise toUpdate) {

    }

    @Override
    public void deleteCustomer(Devise toDelete) {

    }

    @Override
    public Devise getOne(Devise one) throws PropertyNotFoundException {
        return null;
    }
}
