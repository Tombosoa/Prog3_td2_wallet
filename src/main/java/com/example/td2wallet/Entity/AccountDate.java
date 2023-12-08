package com.example.td2wallet.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.OffsetDateTime;

public class AccountDate {
    private int id;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss.SSSSSSXXX")
    private OffsetDateTime transaction_date;
    private String type;
    private double amount;
    private int account_id;
    private String label;
    private String name;
    private double solde;


    public AccountDate(OffsetDateTime transaction_date, String type, double amount, int account_id, String label, double solde, String name) {
        this.transaction_date = transaction_date;
        this.type = type;
        this.amount = amount;
        this.account_id = account_id;
        this.label = label;
        this.name = name;
        this.solde = solde;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public void setId(int id) {
        this.id = id;
    }



    public String getType(String type) {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount(double amount) {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getAccount_id(int accountId) {
        return account_id;
    }
    public double getAmount() {
        return amount;
    }

    public int getAccount_id() {
        return account_id;
    }
    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public String getLabel(String label) {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public double getSolde(double solde) {
        return this.solde;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }


    public AccountDate() {
    }

    public OffsetDateTime getTransaction_date(OffsetDateTime transaction_date) {
        return transaction_date;
    }

    public void setTransaction_date(OffsetDateTime transaction_date) {
        this.transaction_date = transaction_date;
    }



    @Override
    public String toString() {
        return "AccountDate{" +
                "id=" + id +
                ", transaction_date=" + transaction_date +
                ", type='" + type + '\'' +
                ", amount=" + amount +
                ", account_id=" + account_id +
                ", label='" + label + '\'' +
                ", name='" + name + '\'' +
                ", solde=" + solde +
                '}';
    }
}
