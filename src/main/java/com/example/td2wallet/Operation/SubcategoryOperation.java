package com.example.td2wallet.Operation;

import com.example.td2wallet.DataBaseConnection;
import com.example.td2wallet.Entity.Category;
import com.example.td2wallet.Entity.SubCategory;
import jakarta.el.PropertyNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubcategoryOperation implements CrudOperation<SubCategory>{
    String userName = System.getenv("DB_USERNAME");
    String password = System.getenv("DB_PASSWORD");
    String databaseName = System.getenv("DB_NAME");
    DataBaseConnection dbConnection = new DataBaseConnection(userName, password, databaseName);
    Connection conn = dbConnection.getConnection();
    public Statement statement;
    @Override
    public List<SubCategory> findAll() {
        List<SubCategory> subCategoryList = new ArrayList<>();
        try (Statement statement = conn.createStatement()) {
            String query = "SELECT id, subcategory_name,category_id from subcategory";
            try (ResultSet result = statement.executeQuery(query)) {
                while (result.next()) {
                    int id = result.getInt("id"); // cela s'appele des magic numbers
                    String subcategory_name = (result.getString("subcategory_name"));
                    int category_id =(result.getInt("category_id"));
                    SubCategory subcategory= new SubCategory(id,subcategory_name,category_id);
                    subCategoryList.add(subcategory);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching subcategory from the database", e);
        }
        return subCategoryList;
    }

    @Override
    public List<SubCategory> saveAll(List<SubCategory> toSave) {
        List <SubCategory> subCategoryList = new ArrayList<>();

        try {
            for (SubCategory subCategory : toSave) {
                String query = "INSERT INTO subcategory (subcategory_name,category_id) VALUES (?,?) ON CONFLICT (ID) DO UPDATE SET subcategory_name = EXCLUDED.subcategory_name, category_id = EXCLUDED.category_id";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setObject(1, subCategory.getSubcategory_name(), Types.OTHER);
                preparedStatement.setInt(2, subCategory.getCategory_id());
                preparedStatement.executeUpdate();


                subCategoryList.add(subCategory);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return subCategoryList;
    }

    @Override
    public SubCategory save(SubCategory toSave) {
        try {
                String query = "INSERT INTO subcategory (subcategory_name,category_id) VALUES (?,?) ON CONFLICT (ID) DO UPDATE SET subcategory_name = EXCLUDED.subcategory_name, category_id = EXCLUDED.category_id";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setObject(1, toSave.getSubcategory_name(), Types.OTHER);
                preparedStatement.setInt(2, toSave.getCategory_id());
                preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return toSave;
    }

    @Override
    public SubCategory update(SubCategory toUpdate) {
        try {
            String updateQuery = "UPDATE subcategory SET subcategory_name=? , category_id=? WHERE id=?";
            PreparedStatement updateStatement = conn.prepareStatement(updateQuery);
            updateStatement.setObject(1, toUpdate.getSubcategory_name(),Types.OTHER);
            updateStatement.setInt(2,toUpdate.getCategory_id());
            updateStatement.setInt(3, toUpdate.getId());
            updateStatement.executeUpdate();

            String selectQuery = "SELECT * FROM subcategory WHERE id=?";
            PreparedStatement selectStatement = conn.prepareStatement(selectQuery);
            selectStatement.setInt(1, toUpdate.getId());

            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                SubCategory updatedSubCategory = new SubCategory();
                updatedSubCategory.setId(resultSet.getInt("id"));
                updatedSubCategory.setSubcategory_name(resultSet.getString("subcategory_name"));
                updatedSubCategory.setCategory_id(resultSet.getInt("category_id"));


                System.out.println("SubCategory updated");
                return updatedSubCategory;
            } else {
                throw new RuntimeException("Failed to retrieve updated subcategory information.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public SubCategory delete(SubCategory toDelete) {
        return null;
    }

    @Override
    public SubCategory getOne(SubCategory one) throws PropertyNotFoundException {
        return null;
    }

    public static void main(String[] args) {
        SubcategoryOperation subcategoryOperation = new SubcategoryOperation();
      /*  System.out.println(subcategoryOperation.findAll());
        List<SubCategory> subCategoryList = new ArrayList<>();
        subCategoryList.add(subCategory);
        subcategoryOperation.save(subCategory);*/
        SubCategory subCategory = new SubCategory(4,"Gifts",3);
        subcategoryOperation.update(subCategory);
    }
}
