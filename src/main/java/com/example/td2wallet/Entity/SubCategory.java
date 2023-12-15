package com.example.td2wallet.Entity;

public class SubCategory {
    private int id;
    private String subcategory_name;
    private int category_id ;



    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getSubcategory_name() {
        return subcategory_name;
    }

    public SubCategory() {
    }

    public void setSubcategory_name(String subcategory_name) {
        this.subcategory_name = subcategory_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SubCategory(int id, String subcategory_name, int category_id) {
        this.id = id;
        this.subcategory_name = subcategory_name;
        this.category_id = category_id;
    }

    public SubCategory(String subcategory_name, int category_id) {
        this.subcategory_name = subcategory_name;
        this.category_id = category_id;
    }

    @Override
    public String toString() {
        return "SubCategory{" +
                "id=" + id +
                ", subcategory_name='" + subcategory_name + '\'' +
                ", category_id=" + category_id +
                '}';
    }
}
