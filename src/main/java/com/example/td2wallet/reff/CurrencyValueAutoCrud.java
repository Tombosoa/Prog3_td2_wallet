package com.example.td2wallet.reff;

import com.example.td2wallet.reff.Entity.CurrencyValue;

import java.util.List;

public class CurrencyValueAutoCrud extends Service<CurrencyValue>{
    public CurrencyValueAutoCrud(Class<CurrencyValue> entityClass) {
        super(entityClass);
    }

    public static void main(String[] args) {
        Service<CurrencyValue> currencyValueService = Service.createService(CurrencyValue.class);
        List<CurrencyValue> currencyValues = currencyValueService.findAll();
        System.out.println(currencyValues);
    }
}
