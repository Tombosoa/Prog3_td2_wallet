package com.example.td2wallet.Entity;

public class Account extends User {
    private int account_id;
    private String name;
    private String type;
    private double solde;



    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    private String user_id;
    private int currency_id;

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }





    public Account(String id, String username, String email, String name, int account_id, int currency_id) {
        super(id, username, email);
        this.account_id = account_id;
        this.name = name;
        this.currency_id = currency_id;
    }
    public Account( String name,String user_id, int currency_id,String type,double solde) {

        this.name = name;
        this.user_id= user_id;
        this.currency_id = currency_id;
        this.type = type;
        this.solde=solde;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }


    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getCurrency_id() {
        return currency_id;
    }

    public void setCurrency_id(int currency_id) {
        this.currency_id = currency_id;
    }


    public Account(int account_id, String name, String user_id, int currency_id) {
        super();
        this.account_id = account_id;
        this.name = name;
        this.user_id = user_id;
        this.currency_id = currency_id;
    }

    public Account(String name, String user_id, int currency_id) {
        this.name = name;
        this.user_id = user_id;
        this.currency_id = currency_id;
    }

    public Account(String username, String email, String name) {
        super(username, email);
        this.name = name;
    }

    public Account() {
    }

    public Account(int account_id, String name, String username, String email, int currency_id, String user_id) {
        super(username, email);
        this.account_id = account_id;
        this.name = name;
        this.currency_id = currency_id;
        this.user_id = user_id;
    }



    public Account(String id, String username, String email, int account_id, String name, String type, double solde, String user_id, int currency_id) {
        super(id, username, email);
        this.account_id = account_id;
        this.name = name;
        this.type = type;
        this.solde = solde;
        this.user_id = user_id;
        this.currency_id = currency_id;
    }

    public Account(String username, String email, int account_id, String name, String type, double solde, String user_id, int currency_id) {
        super(username, email);
        this.account_id = account_id;
        this.name = name;
        this.type = type;
        this.solde = solde;
        this.user_id = user_id;
        this.currency_id = currency_id;
    }

    public Account(String id, int account_id, String name, String type, double solde, String user_id, int currency_id) {
        super(id);
        this.account_id = account_id;
        this.name = name;
        this.type = type;
        this.solde = solde;
        this.user_id = user_id;
        this.currency_id = currency_id;
    }

    public Account(int account_id, String name, String type, double solde, String user_id, int currency_id) {
        this.account_id = account_id;
        this.name = name;
        this.type = type;
        this.solde = solde;
        this.user_id = user_id;
        this.currency_id = currency_id;
    }

    public Account(String id, String username, String email, String password, int account_id, String name, String type, double solde, String user_id, int currency_id) {
        super(id, username, email, password);
        this.account_id = account_id;
        this.name = name;
        this.type = type;
        this.solde = solde;
        this.user_id = user_id;
        this.currency_id = currency_id;
    }

    @Override
    public String toString() {
        return "Account{" +
                "account_id=" + account_id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", solde=" + solde +
                ", user_id='" + user_id + '\'' +
                ", currency_id=" + currency_id +
                '}';
    }
}
