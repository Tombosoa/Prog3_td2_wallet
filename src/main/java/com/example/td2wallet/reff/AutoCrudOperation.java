package com.example.td2wallet.reff;

import com.example.td2wallet.Operation.CrudOperation;

import java.util.List;

public interface AutoCrudOperation<T> extends CrudOperation<T> {
    @Override
    List<T> findAll();
}
