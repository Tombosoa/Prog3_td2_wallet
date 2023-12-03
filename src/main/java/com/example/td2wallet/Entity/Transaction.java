package com.example.td2wallet.Entity;

import java.time.LocalDate;

public class Transaction {
    private int id;
    private LocalDate transaction_date;
    private String transaction_type;
    private int transaction_price;
    private int account_id;

    public Transaction(int id, LocalDate transaction_date, String transaction_type, int transaction_price, int account_id) {
        this.id = id;
        this.transaction_date = transaction_date;
        this.transaction_type = transaction_type;
        this.transaction_price = transaction_price;
        this.account_id = account_id;
    }
    public Transaction( LocalDate transaction_date, String transaction_type, int transaction_price, int account_id) {
        this.transaction_date = transaction_date;
        this.transaction_type = transaction_type;
        this.transaction_price = transaction_price;
        this.account_id = account_id;
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

    public int getTransaction_price() {
        return transaction_price;
    }

    public void setTransaction_price(int transaction_price) {
        this.transaction_price = transaction_price;
    }

    public LocalDate getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date( LocalDate transaction_date) {
        this.transaction_date = transaction_date;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
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
                ", transaction_type='" + transaction_type + '\'' +
                ", account_id=" + account_id +
                '}';
    }


}
