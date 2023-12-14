package com.example.td2wallet.Entity;

import com.example.td2wallet.Enum.CategoryEnum;
import com.example.td2wallet.Enum.TypeEnum;

public class Category {
    private int id;
    private TypeEnum type;
    private CategoryEnum category_name;

    public Category(int id, TypeEnum type, CategoryEnum category_name) {
        this.id = id;
        this.type = type;
        this.category_name = category_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TypeEnum getType() {
        return type;
    }

    public void setType(TypeEnum type) {
        this.type = type;
    }

    public CategoryEnum getCategory_name() {
        return category_name;
    }

    public void setCategory_name(CategoryEnum category_name) {
        this.category_name = category_name;
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
