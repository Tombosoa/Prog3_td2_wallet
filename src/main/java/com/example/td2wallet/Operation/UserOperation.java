package com.example.td2wallet.Operation;

import com.example.td2wallet.DataBaseConnection;
import com.example.td2wallet.Entity.User;
import jakarta.el.PropertyNotFoundException;
import org.springframework.stereotype.Component;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class UserOperation implements CrudOperation<User> {
    String userName = System.getenv("DB_USERNAME");
    String password = System.getenv("DB_PASSWORD");
    String databaseName = System.getenv("DB_NAME");
    DataBaseConnection dbConnection = new DataBaseConnection(userName, password, databaseName);
    Connection conn = dbConnection.getConnection();
    public Statement statement;

    @Override
    public List<User> findAll() {
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
            throw new RuntimeException("Error fetching users from the database", e);
        }
        return userList;
    }

    @Override
    public List<User> saveAll(List<User> toSave) {
        List<User> savedUsers = new ArrayList<>();
        try {
            for (User user : toSave) {
                String query = "INSERT INTO \"user\" (username, email, password) VALUES (?, ?, ?)";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, user.getUsername());
                preparedStatement.setString(2, user.getEmail());
                preparedStatement.setString(3, user.getPassword());

                preparedStatement.executeUpdate();
                savedUsers.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return savedUsers;
    }


    @Override
    public User save(User toAdd) {
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

    public User getOne(UUID id) throws PropertyNotFoundException {
        try {
            String query = "SELECT * FROM \"user\" WHERE id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setObject(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                User user = new User();
                user.setId(String.valueOf(UUID.fromString(resultSet.getString("id"))));
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));

                return user;
            } else {
                throw new PropertyNotFoundException("User not found with id: " + id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public User update(User toUpdate) {
        try {
            String updateQuery = "UPDATE \"user\" SET username=?, email=? WHERE id=CAST(? AS UUID)";
            PreparedStatement updateStatement = conn.prepareStatement(updateQuery);
            updateStatement.setString(1, toUpdate.getUsername());
            updateStatement.setString(2, toUpdate.getEmail());
            updateStatement.setObject(3, toUpdate.getId());
            updateStatement.executeUpdate();

            String selectQuery = "SELECT * FROM \"user\" WHERE id=CAST(? AS UUID)";
            PreparedStatement selectStatement = conn.prepareStatement(selectQuery);
            selectStatement.setObject(1, toUpdate.getId());

            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                User updatedUser = new User();
                updatedUser.setId(String.valueOf(UUID.fromString(resultSet.getString("id"))));
                updatedUser.setUsername(resultSet.getString("username"));
                updatedUser.setEmail(resultSet.getString("email"));


                System.out.println("User updated");
                return updatedUser;
            } else {
                throw new RuntimeException("Failed to retrieve updated user information.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public User delete(User toDelete) {
        return null;
    }

    public void deleteUser(UUID toDelete) {
        try {
            String query = "DELETE FROM \"user\" WHERE id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setObject(1, toDelete);
            preparedStatement.executeUpdate();

            System.out.println("user deleted");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public User getOne(User one) throws PropertyNotFoundException {
        return null;
    }
}
