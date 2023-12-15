package com.example.td2wallet.Entity;

public class Category {
    private int id;
    private String type;
    private String category_name;

    public Category(int id, String type, String category_name) {
        this.id = id;
        this.type = type;
        this.category_name = category_name;
    }

    public Category() {

    }

    public Category(String type, String category_name) {
        this.type = type;
        this.category_name = category_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_name() {
        return category_name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", type=" + type +
                ", category_name=" + category_name +
                '}';
    }
}
