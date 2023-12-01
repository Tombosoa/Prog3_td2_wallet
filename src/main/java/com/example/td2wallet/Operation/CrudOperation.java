package com.example.td2wallet.Operation;

import jakarta.el.PropertyNotFoundException;

import java.util.List;

public interface CrudOperation<T> {
    public List<T> getAll();
    public T add(T toAdd);
    public void updateCustomer(T toUpdate);
    void deleteCustomer(T toDelete);
    T getOne(T one) throws PropertyNotFoundException;
}
