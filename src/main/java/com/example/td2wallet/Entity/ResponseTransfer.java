package com.example.td2wallet.Entity;

import java.sql.Date;

public class ResponseTransfer {
    private Account AccountDebit;
    private Account accountCredit;
    private double amount;
    private Date date;

    public ResponseTransfer(Account accountDeb, Account accountCred) {
        this.accountCredit = accountCred;
        this.AccountDebit = accountDeb;
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

    public ResponseTransfer(Account accountDebit, Account accountCredit, double amount, Date date) {
        AccountDebit = accountDebit;
        this.accountCredit = accountCredit;
        this.amount = amount;
        this.date = date;
    }

    @Override
    public String toString() {
        return "ResponseTransfer{" +
                "AccountDebit=" + AccountDebit +
                ", accountCredit=" + accountCredit +
                ", amount=" + amount +
                ", date=" + date +
                '}';
    }
}
