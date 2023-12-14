package com.example.td2wallet.Entity;

import com.example.td2wallet.Enum.SubCategoryEnum;

public class SubCategory {
    private int id;
    private SubCategoryEnum subcategory_name;

    public SubCategory(int id, SubCategoryEnum subcategory_name) {
        this.id = id;
        this.subcategory_name = subcategory_name;
    }

    public SubCategory(SubCategoryEnum subcategory_name) {
        this.subcategory_name = subcategory_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SubCategoryEnum getSubcategory_name() {
        return subcategory_name;
    }

    public void setSubcategory_name(SubCategoryEnum subcategory_name) {
        this.subcategory_name = subcategory_name;
    }

    @Override
    public String toString() {
        return "SubCategory{" +
                "id=" + id +
                ", subcategory_name=" + subcategory_name +
                '}';
    }
}
