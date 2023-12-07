package com.example.td2wallet.Entity;

import lombok.Getter;
import lombok.Setter;

public class Devise {
    private int id;
    private String name;
    private String code;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Devise(int id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

    public Devise(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public Devise() {
    }

    @Override
    public String toString() {
        return "Devise{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
