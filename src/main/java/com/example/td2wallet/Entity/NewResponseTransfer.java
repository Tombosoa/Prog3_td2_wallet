package com.example.td2wallet.Entity;

import java.sql.Date;

public class NewResponseTransfer {
    private Account AccountDebit;
    private Account accountCredit;
    private double amount;
    private Date date;
    private String category_name;
    private String subcategory_name;

    private Account account;

    public NewResponseTransfer(Account account) {
        this.account = account;
    }

    public Account getAccountDebit() {
        return AccountDebit;
    }

    public void setAccountDebit(Account accountDebit) {
        AccountDebit = accountDebit;
    }

    public Account getAccountCredit() {
        return accountCredit;
    }

    public void setAccountCredit(Account accountCredit) {
        this.accountCredit = accountCredit;
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

    public NewResponseTransfer(Account accountDebit, Account accountCredit, double amount, Date date, String category_name, String subcategory_name) {
        AccountDebit = accountDebit;
        this.accountCredit = accountCredit;
        this.amount = amount;
        this.date = date;
        this.category_name = category_name;
        this.subcategory_name = subcategory_name;
    }

    public NewResponseTransfer(Account accountDebit, Account accountCredit) {
        AccountDebit = accountDebit;
        this.accountCredit = accountCredit;
    }

    @Override
    public String toString() {
        return "NewResponseTransfer{" +
                "AccountDebit=" + AccountDebit +
                ", accountCredit=" + accountCredit +
                ", amount=" + amount +
                ", date=" + date +
                ", category_name='" + category_name + '\'' +
                ", subcategory_name='" + subcategory_name + '\'' +
                '}';
    }
}
