package com.example.td2wallet.Operation;

import com.example.td2wallet.DataBaseConnection;
import com.example.td2wallet.Entity.Account;
import jakarta.el.PropertyNotFoundException;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class AccountOperation implements CrudOperation<Account>{
    String userName = System.getenv("DB_USERNAME");
    String password = System.getenv("DB_PASSWORD");
    String databaseName = System.getenv("DB_NAME");
    DataBaseConnection dbConnection = new DataBaseConnection(userName, password, databaseName);
    Connection conn = dbConnection.getConnection();
    public Statement statement;
    @Override
    public List<Account> findAll() {
        List<Account> accountList = new ArrayList<>();
        try (Statement statement = conn.createStatement()) {
            String query = "SELECT username, email, account_name from \"user\" inner join account on account.user_id = \"user\".id";
            try (ResultSet result = statement.executeQuery(query)) {
                while (result.next()) {
                    String username = result.getString("username");
                    String email = result.getString("email");
                    String account_name = result.getString("account_name");

                    Account account = new Account(username, email, account_name);
                    accountList.add(account);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching users from the database", e);
        }
        return accountList;
    }

    @Override
    public List<Account> saveAll(List<Account> toSave) {
        List<Account> savedAccounts = new ArrayList<>();
        try {
            for (Account account : toSave) {
                String query = "INSERT INTO account (account_name, user_id, devise_id) VALUES (?, ?, ?)";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, account.getAccount_name());
                preparedStatement.setObject(2, account.getUser_id());
                preparedStatement.setInt(3, account.getDevise_id());

                preparedStatement.executeUpdate();
                savedAccounts.add(account);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return savedAccounts;
    }

    @Override
    public Account save(Account toAdd) {
        return null;
    }

    @Override
    public Account update(Account toUpdate) {
return null;
    }

    @Override
    public Account delete(Account toDelete) {
        return null;
    }

    @Override
    public Account getOne(Account one) throws PropertyNotFoundException {
        return null;
    }
}
