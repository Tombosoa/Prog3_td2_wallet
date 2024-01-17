package com.example.td2wallet.reff;


import java.lang.reflect.*;

public class Ref {
    public static void main(String[] args) {
        try {
            Class<?> myClass = Class.forName("com.example.td2wallet.Operation.CategoryOperation");
            Class<?> category = Class.forName("com.example.td2wallet.Entity.Category");
            Object categoryOperationInstance = myClass.getDeclaredConstructor().newInstance();

            Method findAllMethod =  myClass.getMethod("findAll");
            Field[] champs = category.getDeclaredFields();
            System.out.println(findAllMethod.invoke(categoryOperationInstance));
            for (Field champ: champs){
                System.out.println("Nom du champ: " + champ.getName());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
