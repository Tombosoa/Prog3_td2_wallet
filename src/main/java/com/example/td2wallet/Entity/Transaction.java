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
    private int category_id;
    private int subcategory_id;




    public int getSubcategory_id() {
        return subcategory_id;
    }

    public void setSubcategory_id(int subcategory_id) {
        this.subcategory_id = subcategory_id;
    }

    public Transaction(int id, LocalDate transaction_date, String type, double amount, int account_id, String label, int category_id, int subcategory_id) {
        this.id = id;
        this.transaction_date = transaction_date;
        this.type = type;
        this.amount = amount;
        this.account_id = account_id;
        this.label = label;
        this.category_id = category_id;
        this.subcategory_id = subcategory_id;
    }

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


    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public Transaction(int id, LocalDate transaction_date, String type, double amount, int account_id, String label, int category_id) {
        this.id = id;
        this.transaction_date = transaction_date;
        this.type = type;
        this.amount = amount;
        this.account_id = account_id;
        this.label = label;
        this.category_id = category_id;
    }

    public Transaction(LocalDate transaction_date, String type, double amount, int account_id, String label, int category_id, int subcategory_id) {
        this.transaction_date = transaction_date;
        this.type = type;
        this.amount = amount;
        this.account_id = account_id;
        this.label = label;
        this.category_id = category_id;
        this.subcategory_id = subcategory_id;
    }

    public Transaction(int id) {
        this.id = id;
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

    public void setTransaction_date(LocalDate transaction_date) {
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
                ", category_id=" + category_id +
                '}';
    }
}
