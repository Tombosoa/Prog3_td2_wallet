package com.example.td2wallet.Entity;

import java.sql.Date;

public class NewResponseTransfer {
    private Account account;

    private double amount;
    private Date date;
    private String category_name;
    private String subcategory_name;


    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getSubcategory_name() {
        return subcategory_name;
    }

    public void setSubcategory_name(String subcategory_name) {
        this.subcategory_name = subcategory_name;
    }



    public NewResponseTransfer(Account account, double amount) {
        this.account = account;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "NewResponseTransfer{" +
                "account=" + account +
                ", amount=" + amount +
                ", date=" + date +
                ", category_name='" + category_name + '\'' +
                ", subcategory_name='" + subcategory_name + '\'' +
                '}';
    }
}
