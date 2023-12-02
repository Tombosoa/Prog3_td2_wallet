package com.example.td2wallet.Entity;

import lombok.Getter;
import lombok.Setter;

public class Devise {
    private int id;
    private String devise_name;
    private String devise_country;

    public Devise(String deviseName, String deviseCountry) {
        this.devise_name =deviseName;
        this.devise_country=deviseCountry;
    }

    public Devise(int id, String deviseName, String deviseCountry) {
       this.id=id;
        this.devise_name =deviseName;
        this.devise_country=deviseCountry;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDevise_name() {
        return devise_name;
    }

    public void setDevise_name(String devise_name) {
        this.devise_name = devise_name;
    }

    public String getDevise_country() {
        return devise_country;
    }

    public void setDevise_country(String devise_country) {
        this.devise_country = devise_country;
    }

    public Devise() {
    }

    public Devise(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Devise{" +
                "id=" + id +
                ", devise_name='" + devise_name + '\'' +
                ", devise_country='" + devise_country + '\'' +
                '}';
    }
}
