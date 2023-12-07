package com.example.td2wallet.functionnality;
import com.example.td2wallet.DataBaseConnection;
import com.example.td2wallet.Entity.Account;
import com.example.td2wallet.Entity.Transaction;
import com.example.td2wallet.Entity.User;
import jakarta.el.PropertyNotFoundException;
import org.springframework.stereotype.Component;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class Functionnality {
    String userName = System.getenv("DB_USERNAME");
    String password = System.getenv("DB_PASSWORD");
    String databaseName = System.getenv("DB_NAME");
    DataBaseConnection dbConnection = new DataBaseConnection(userName, password, databaseName);
    Connection conn = dbConnection.getConnection();
    public Statement statement;
    public Account makeTransaction(Transaction transaction){
        try{
            String query = "INSERT INTO transaction (type,amount,account_id, label) VALUES ( ?, ?,?,?)";
            PreparedStatement insertStatement = conn.prepareStatement(query);
            insertStatement.setString(1, transaction.getType());
            insertStatement.setDouble(2, transaction.getAmount());
            insertStatement.setInt(3, transaction.getAccount_id());
            insertStatement.setString(4, transaction.getLabel());
            insertStatement.executeUpdate(); // N'oubliez pas d'exécuter l'insertion

            Account survol = new Account();
            String selectAccount = "select * from account where id = ?";
            PreparedStatement selectStatement = conn.prepareStatement(selectAccount);
            selectStatement.setInt(1, transaction.getAccount_id());

            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                survol.setAccount_id(resultSet.getInt("id"));
                survol.setName(resultSet.getString("name"));
                survol.setUser_id(String.valueOf(UUID.fromString(resultSet.getString("user_id"))));
                survol.setCurrency_id(resultSet.getInt("currency_id"));
                survol.setType(resultSet.getString("type"));
                survol.setSolde(resultSet.getDouble("solde"));
            }

            String updateAccount = "UPDATE account SET solde = ? WHERE id = ?";
            PreparedStatement newStatement = conn.prepareStatement(updateAccount);
            if (transaction.getType().equals("Debit")){
                newStatement.setDouble(1, survol.getSolde() + transaction.getAmount());
                newStatement.setInt(2, transaction.getAccount_id());
            }else if(transaction.getType().equals("Credit")){
                newStatement.setDouble(1, survol.getSolde() - transaction.getAmount());
                newStatement.setInt(2, transaction.getAccount_id());
            }
            newStatement.executeUpdate();

            Account result = new Account();
            String resultAccount = "select * from account where id = ?";
            PreparedStatement resultSelectStatement = conn.prepareStatement(resultAccount);
            resultSelectStatement.setInt(1, transaction.getAccount_id());

            ResultSet accountresultSet = resultSelectStatement.executeQuery();

            if (accountresultSet.next()) {
                survol.setAccount_id(accountresultSet.getInt("id"));
                survol.setName(accountresultSet.getString("name"));
                survol.setUser_id(String.valueOf(UUID.fromString(accountresultSet.getString("user_id"))));
                survol.setCurrency_id(accountresultSet.getInt("currency_id"));
                survol.setType(accountresultSet.getString("type"));
                survol.setSolde(accountresultSet.getDouble("solde"));

                return survol;
            } else {
                throw new RuntimeException("Failed to retrieve updated account information.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
