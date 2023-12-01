package com.example.td2wallet.Operation;

import com.example.td2wallet.DataBaseConnection;
import com.example.td2wallet.Entity.User;
import jakarta.el.PropertyNotFoundException;
import org.springframework.stereotype.Component;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserOperation implements CrudOperation<User> {
    String userName = System.getenv("DB_USERNAME");
    String password = System.getenv("DB_PASSWORD");
    String databaseName = System.getenv("DB_NAME");
    DataBaseConnection dbConnection = new DataBaseConnection(userName, password, databaseName);
    Connection conn = dbConnection.getConnection();
    public Statement statement;

    @Override
    public List<User> getAll() {
        List<User> userList = new ArrayList<>();
        try (Statement statement = conn.createStatement()) {
            String query = "SELECT * FROM \"user\"";
            try (ResultSet result = statement.executeQuery(query)) {
                while (result.next()) {
                    String id = result.getString("id");
                    String username = result.getString("username");
                    String email = result.getString("email");
                    String password = result.getString("password");
                    User user = new User(id, username, email, password);
                    userList.add(user);
                }
            }
        } catch (SQLException e) {
            // Handle or log the exception appropriately
            throw new RuntimeException("Error fetching users from the database", e);
        }
        return userList;
    }


    @Override
    public User add(User toAdd) {
        try {
            String query = "INSERT INTO \"user\" (username, email, password) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, toAdd.getUsername());
            preparedStatement.setString(2, toAdd.getEmail());
            preparedStatement.setString(3, toAdd.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return toAdd;
    }

    @Override
    public void updateCustomer(User toUpdate) {

    }

    @Override
    public void deleteCustomer(User toDelete) {

    }

    @Override
    public User getOne(User one) throws PropertyNotFoundException {
        return null;
    }
}
