package com.example.td2wallet.Operation;

import com.example.td2wallet.DataBaseConnection;
import com.example.td2wallet.Entity.Category;
import com.example.td2wallet.Entity.Devise;
import jakarta.el.PropertyNotFoundException;

import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryOperation  implements CrudOperation <Category>{
    String userName = System.getenv("DB_USERNAME");
    String password = System.getenv("DB_PASSWORD");
    String databaseName = System.getenv("DB_NAME");
    DataBaseConnection dbConnection = new DataBaseConnection(userName, password, databaseName);
    Connection conn = dbConnection.getConnection();
    public Statement statement;

    @Override
    public List findAll() {
        List<Category> categoryList = new ArrayList<>();
        try (Statement statement = conn.createStatement()) {
            String query = "SELECT id, type, category_name from category ";
            try (ResultSet result = statement.executeQuery(query)) {
                while (result.next()) {
                    int id = result.getInt("id"); // cela s'appele des magic numbers
                    String type = (result.getString("type"));
                    String category_name =(result.getString("category_name"));

                   Category category = new Category(id,type,category_name);
                    categoryList.add(category);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching category from the database", e);
        }
        return categoryList;
    }

    @Override
    public List<Category> saveAll (List <Category> toSave) {
        List <Category> categoryList = new ArrayList<>();

        try {
            for (Category category : toSave) {
                String query = "INSERT INTO category (type,category_name) VALUES (?,?) ON CONFLICT (ID) DO UPDATE SET type = EXCLUDED.type, category_name = EXCLUDED.category_name";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setObject(1, category.getType(), Types.OTHER);
                preparedStatement.setObject(2, category.getCategory_name(), Types.OTHER);
                preparedStatement.executeUpdate();


                categoryList.add(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categoryList;
    }

    @Override
    public Category save(Category toAdd) {
        try {
                String query = "INSERT INTO category (type,category_name) VALUES (?,?) ON CONFLICT (ID) DO UPDATE SET type = EXCLUDED.type, category_name = EXCLUDED.category_name";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setObject(1, toAdd.getType(), Types.OTHER);
                preparedStatement.setObject(2, toAdd.getCategory_name(), Types.OTHER);
                preparedStatement.executeUpdate();
            }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return toAdd;
    }

    @Override
    public Category update(Category toUpdate) {
        try {
            String updateQuery = "UPDATE category SET type=? , category_name=? WHERE id=?";
            PreparedStatement updateStatement = conn.prepareStatement(updateQuery);
            updateStatement.setObject(1, toUpdate.getType(),Types.OTHER);
            updateStatement.setObject(2,toUpdate.getCategory_name(),Types.OTHER);
            updateStatement.setInt(3, toUpdate.getId());
            updateStatement.executeUpdate();

            String selectQuery = "SELECT * FROM category WHERE id=?";
            PreparedStatement selectStatement = conn.prepareStatement(selectQuery);
            selectStatement.setInt(1, toUpdate.getId());

            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                Category updatedCategory = new Category();
                updatedCategory.setId(resultSet.getInt("id"));
                updatedCategory.setType(resultSet.getString("type"));
                updatedCategory.setCategory_name(resultSet.getString("category_name"));


                System.out.println("Category updated");
                return updatedCategory;
            } else {
                throw new RuntimeException("Failed to retrieve updated category information.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Category delete(Category toDelete) {
        return null;
    }

    @Override
    public Category getOne(Category one) throws PropertyNotFoundException {
        return null;
    }

}
