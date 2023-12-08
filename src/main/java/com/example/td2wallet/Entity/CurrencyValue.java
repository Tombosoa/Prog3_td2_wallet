package com.example.td2wallet.Entity;

import java.time.LocalDate;

public class CurrencyValue {
    private int id;
    private int id_currency_source;
    private int id_currency_destination;
    private double amount;
    private LocalDate release;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_currency_source() {
        return id_currency_source;
    }

    public void setId_currency_source(int id_currency_source) {
        this.id_currency_source = id_currency_source;
    }

    public int getId_currency_destination() {
        return id_currency_destination;
    }

    public void setId_currency_destination(int id_currency_destination) {
        this.id_currency_destination = id_currency_destination;
    }

    public LocalDate getRelease() {
        return release;
    }

    public void setRelease(LocalDate release) {
        this.release = release;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public CurrencyValue() {
    }

    public CurrencyValue(int id, int id_currency_source, int id_currency_destination, double amount, LocalDate release) {
        this.id = id;
        this.id_currency_source = id_currency_source;
        this.id_currency_destination = id_currency_destination;
        this.amount = amount;
        this.release = release;
    }

    @Override
    public String toString() {
        return "CurrencyValue{" +
                "id=" + id +
                ", id_currency_source=" + id_currency_source +
                ", id_currency_destination=" + id_currency_destination +
                ", amount=" + amount +
                ", release=" + release +
                '}';
    }
}
