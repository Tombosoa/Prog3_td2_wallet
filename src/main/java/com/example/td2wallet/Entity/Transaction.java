package com.example.td2wallet.Entity;

import java.sql.Date;
import java.time.LocalDate;

public class Transaction {
    private int id;
    private LocalDate transaction_date;
    private String type;
    private double amount;
    private int account_id;

    private String label;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Transaction() {

    }

    public Transaction(int id) {
        this.id=id;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public LocalDate getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date( LocalDate transaction_date) {
        this.transaction_date = transaction_date;
    }

    public Transaction(int id, LocalDate transaction_date, String type, double amount, int account_id, String label) {
        this.id = id;
        this.transaction_date = transaction_date;
        this.type = type;
        this.amount = amount;
        this.account_id = account_id;
        this.label = label;
    }

    public Transaction(LocalDate transaction_date, String type, double amount, int account_id, String label) {
        this.transaction_date = transaction_date;
        this.type = type;
        this.amount = amount;
        this.account_id = account_id;
        this.label = label;
    }



    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }


    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", transaction_date=" + transaction_date +
                ", type='" + type + '\'' +
                ", amount=" + amount +
                ", account_id=" + account_id +
                ", label='" + label + '\'' +
                '}';
    }
}
