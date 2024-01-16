package com.example.td2wallet.reff.Entity;

import com.example.td2wallet.Entity.User;

public class Account {
    private int id;
    private String name;
    private String type;
    private double solde;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public Account(int id, String name, String type, double solde) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.solde = solde;
    }
    public Account() {
    }
    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", solde=" + solde +
                '}';
    }
}
