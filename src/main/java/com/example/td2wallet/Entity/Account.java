package com.example.td2wallet.Entity;

import lombok.Getter;
import lombok.Setter;


public class Account {
    private int id;
    private String account_type;

    private String user_id;
    private int devise_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
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

    public Account() {
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", account_type='" + account_type + '\'' +
                ", user_id='" + user_id + '\'' +
                ", devise_id=" + devise_id +
                '}';
    }
}
