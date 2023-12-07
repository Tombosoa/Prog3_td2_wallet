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
import java.util.UUID;

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
            String query = "SELECT account.id as account_id, account_name, devise_id, \"user\".id as user_id, username, email FROM account INNER JOIN \"user\" ON account.user_id = \"user\".id";
            try (ResultSet result = statement.executeQuery(query)) {
                while (result.next()) {
                    int account_id = result.getInt("account_id");
                    String account_name = result.getString("account_name");
                    int devise_id = result.getInt("devise_id");
                    String user_id = result.getString("user_id");
                    String username = result.getString("username");
                    String email = result.getString("email");

                    Account account = new Account(account_id, account_name, username, email, devise_id, user_id);
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
                String query = "INSERT INTO account (account_name, user_id, devise_id) VALUES (?, ?, ?)ON CONFLICT (id) DO UPDATE SET account_name = EXCLUDED.account_name,user_id=EXCLUDED.user_id,devise_id=EXCLUDED.devise_id";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, account.getAccount_name());
                preparedStatement.setObject(2, UUID.fromString(account.getUser_id()));
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
        try {
            String query = "INSERT INTO account (account_name, user_id, devise_id) VALUES (?, CAST(? AS UUID), ?) ON CONFLICT (id) DO UPDATE SET account_name = EXCLUDED.account_name,user_id=EXCLUDED.user_id,devise_id=EXCLUDED.devise_id";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, toAdd.getAccount_name());
            preparedStatement.setString(2, toAdd.getUser_id());
            preparedStatement.setInt(3, toAdd.getDevise_id());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return toAdd;
    }


    public static void main(String[] args) {
        AccountOperation accountOperation = new AccountOperation();
        Account account = new Account("B0AH", "d4bfd8d9-bb39-427b-9977-b92d0b3e8db0",9);
        Account account1=new Account("BMOI","d4bfd8d9-bb39-427b-9977-b92d0b3e8db0",9);
    List<Account> accountList = new ArrayList<>();
    accountList.add(account);
    accountList.add(account1);
    accountOperation.saveAll(accountList);

    }
    @Override
    public Account update(Account toUpdate) {
        try {
            String updateQuery = "UPDATE account SET account_name=? WHERE id=?";
            PreparedStatement updateStatement = conn.prepareStatement(updateQuery);
            updateStatement.setString(1, toUpdate.getAccount_name());
            updateStatement.setInt(2, toUpdate.getAccount_id());
            updateStatement.executeUpdate();

            String selectQuery = "SELECT * FROM account WHERE id=?";
            PreparedStatement selectStatement = conn.prepareStatement(selectQuery);
            selectStatement.setInt(1, toUpdate.getAccount_id());

            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                Account updatedAccount = new Account();
                updatedAccount.setAccount_id(resultSet.getInt("id"));
                updatedAccount.setAccount_name(resultSet.getString("account_name"));
                updatedAccount.setUser_id(String.valueOf(UUID.fromString(resultSet.getString("user_id"))));
                updatedAccount.setDevise_id(resultSet.getInt("devise_id"));

                System.out.println("Account updated");
                return updatedAccount;
            } else {
                throw new RuntimeException("Failed to retrieve updated account information.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Account delete(Account toDelete) {
        return null;
    }


    @Override
    public Account getOne(Account one) throws PropertyNotFoundException {
        return null;
    }

    public Account getOne(int id) throws PropertyNotFoundException {
        try {
            String query = "SELECT account.id as id, account_name, devise_id, \"user\".id as user_id, username, email FROM account inner join \"user\" on account.user_id = \"user\".id WHERE account.id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setObject(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Account account = new Account();
                account.setAccount_id(resultSet.getInt("id"));
                account.setAccount_name(resultSet.getString("account_name"));
                account.setUser_id(String.valueOf(UUID.fromString(resultSet.getString("user_id"))));
                account.setDevise_id(resultSet.getInt("devise_id"));
                account.setEmail(resultSet.getString("email"));
                account.setUsername(resultSet.getString("username"));
                account.setUser_id(resultSet.getString("user_id"));
                account.setId(resultSet.getString("user_id"));

                return account;
            } else {
                throw new PropertyNotFoundException("Account not found with id: " + id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
