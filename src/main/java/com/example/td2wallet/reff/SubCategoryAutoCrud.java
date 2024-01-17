package com.example.td2wallet.reff;

import com.example.td2wallet.Entity.SubCategory;

import java.util.List;

public class SubCategoryAutoCrud extends Service<SubCategory>{
    public SubCategoryAutoCrud(Class<SubCategory> entityClass) {
        super(entityClass);
    }

    public static void main(String[] args) {
        Service<SubCategory> subCategoryService = Service.createService(SubCategory.class);
        List<SubCategory> subCategories = subCategoryService.findAll();

        System.out.println(subCategories);
    }
}
