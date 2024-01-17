package com.example.td2wallet.reff;

import com.example.td2wallet.reff.Entity.Category;
import com.example.td2wallet.reff.Entity.CategoryName;
import com.example.td2wallet.reff.Entity.CategoryType;

import java.lang.reflect.Type;
import java.sql.Types;
import java.util.List;

public class CategoryAutoCrud extends Service<Category> {

    public CategoryAutoCrud(Class<Category> entityClass) {
        super(entityClass);
    }

    public static void main(String[] args) {
        Service<Category> categoryService = Service.createService(Category.class);
        Category newCategory = new Category();
        newCategory.setType(CategoryType.Outgoing);
        newCategory.setCategoryName(CategoryName.Housing);
     //   System.out.println(categoryService.save(newCategory));

        List<Category> categories = categoryService.findAll();
        System.out.println(categories);
    }

}
