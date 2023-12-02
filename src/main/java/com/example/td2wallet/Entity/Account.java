package com.example.td2wallet.Entity;



public class Account extends User{
    private int account_id;
    private String account_name;

    private String user_id;
    private int devise_id;

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getDevise_id() {
        return devise_id;
    }

    public void setDevise_id(int devise_id) {
        this.devise_id = devise_id;
    }

    public Account(String account_name, String user_id, int devise_id) {
        this.account_name = account_name;
        this.user_id = user_id;
        this.devise_id = devise_id;
    }

    public Account(String username, String email, String account_name) {
        super(username, email);
        this.account_name = account_name;
    }
    public Account() {
    }

    public Account(int account_id, String account_name, String user_id, int devise_id) {
        this.account_id = account_id;
        this.account_name = account_name;
        this.user_id = user_id;
        this.devise_id = devise_id;
    }

    @Override
    public String toString() {
        return "Account{" +
                "account_id=" + account_id +
                ", account_name='" + account_name + '\'' +
                ", user_id='" + user_id + '\'' +
                ", devise_id=" + devise_id +
                '}';
    }
}
