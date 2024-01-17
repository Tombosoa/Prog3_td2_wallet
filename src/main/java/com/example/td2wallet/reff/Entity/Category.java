package com.example.td2wallet.reff.Entity;


public class Category {
    private int id;
    private CategoryType type;
    private CategoryName category_name;

    public Category(int id, CategoryType type, CategoryName category_name) {
        this.id = id;
        this.type = type;
        this.category_name = category_name;
    }

    public Category() {

    }

    public Category(CategoryType type, CategoryName category_name) {
        this.type = type;
        this.category_name = category_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CategoryType getType() {
        return type;
    }

    public void setType(CategoryType type) {
        this.type = type;
    }

    public void setCategoryName(CategoryName category_name) {
        this.category_name = category_name;
    }

    public CategoryName getCategoryName() {
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
