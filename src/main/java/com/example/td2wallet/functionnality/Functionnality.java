package com.example.td2wallet.functionnality;
import com.example.td2wallet.DataBaseConnection;
import com.example.td2wallet.Entity.*;
import jakarta.el.PropertyNotFoundException;
import org.springframework.stereotype.Component;


import java.sql.*;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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
            insertStatement.executeUpdate();

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
                newStatement.setDouble(1, survol.getSolde() - transaction.getAmount());
                newStatement.setInt(2, transaction.getAccount_id());
            }else if(transaction.getType().equals("Credit")){
                newStatement.setDouble(1, survol.getSolde() + transaction.getAmount());
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
    public static String transformSpaceToT(String originalDate) {
        return originalDate.replace(" ", "T");
    }
    public AccountDate getByDate(String transaction_date, int account_id) throws PropertyNotFoundException {
        try {
            String query = "SELECT transaction.id AS id, transaction_date, transaction.type as type, amount, account_id, label, solde, currency.name as name FROM account INNER JOIN transaction ON transaction.account_id = account.id INNER JOIN currency ON currency.id = account.currency_id WHERE transaction_date = ? AND account.id = ?";
            PreparedStatement preparedStatemente = conn.prepareStatement(query);

                String transformedDate = transformSpaceToT(transaction_date);
                OffsetDateTime dateTime = OffsetDateTime.parse(transformedDate);

                preparedStatemente.setTimestamp(1, Timestamp.from(dateTime.toInstant()));
                preparedStatemente.setInt(2, account_id);

                ResultSet resultSett = preparedStatemente.executeQuery();
                    if (resultSett.next()) {
                        Timestamp timestampFromResultSet = resultSett.getTimestamp("transaction_date");
                        OffsetDateTime transacDate = OffsetDateTime.ofInstant(timestampFromResultSet.toInstant(), ZoneId.systemDefault());
                        String type = resultSett.getString("type");
                        double amount = resultSett.getDouble("amount");
                        int account_Id = resultSett.getInt("account_id");
                        String label = resultSett.getString("label");
                        double solde = resultSett.getDouble("solde");
                        String name = resultSett.getString("name");
                        AccountDate account = new AccountDate(transacDate, type, amount, account_Id, label, solde, name);
                        return  account;
                    } else {
                        throw new PropertyNotFoundException("Account not found");
                    }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
