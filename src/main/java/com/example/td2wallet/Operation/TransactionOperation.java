package com.example.td2wallet.Operation;

import com.example.td2wallet.DataBaseConnection;
import com.example.td2wallet.Entity.Devise;
import com.example.td2wallet.Entity.Transaction;
import jakarta.el.PropertyNotFoundException;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransactionOperation implements CrudOperation<Transaction> {
    String userName = System.getenv("DB_USERNAME");
    String password = System.getenv("DB_PASSWORD");
    String databaseName = System.getenv("DB_NAME");
    DataBaseConnection dbConnection = new DataBaseConnection(userName, password, databaseName);
    Connection conn = dbConnection.getConnection();
    public Statement statement;
    @Override
    public List<Transaction> findAll() {
        List<Transaction> deviseList = new ArrayList<>();
        try (Statement statement = conn.createStatement()) {
            String query = "SELECT * from transaction ";
            try (ResultSet result = statement.executeQuery(query)) {
                while (result.next()) {
                    int id = result.getInt("id");
                    LocalDate transaction_date = result.getDate("transaction_date").toLocalDate();
                    String transaction_type = result.getString("transaction_type");
                    int transaction_price = result.getInt("transaction_price");
                    int account_id = result.getInt("account_id");

                    Transaction transaction = new Transaction(id,transaction_date,transaction_type,transaction_price,account_id);
                    deviseList.add(transaction);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching transaction from the database", e);
        }
        return deviseList;
    }

    @Override
    public List<Transaction> saveAll(List<Transaction> toSave) {
        List<Transaction> savedTransactions = new ArrayList<>();
        try {
            for (Transaction transaction : toSave) {
                String query = "INSERT INTO transaction (transaction_date,transaction_type,transaction_price,account_id) VALUES ( ?, ?,?,?)";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setObject(1, transaction.getTransaction_date());
                preparedStatement.setString(2, transaction.getTransaction_type());
                preparedStatement.setInt(3, transaction.getTransaction_price());
                preparedStatement.setInt(4,transaction.getAccount_id());


                preparedStatement.executeUpdate();
                savedTransactions.add(transaction);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return savedTransactions;
    }

    @Override
    public Transaction save(Transaction toAdd) {
        try {
            String query = "INSERT INTO transaction (transaction_date,transaction_type,transaction_price,account_id) VALUES ( ?, ?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setObject(1, toAdd.getTransaction_date());
            preparedStatement.setString(2, toAdd.getTransaction_type());
            preparedStatement.setInt(3, toAdd.getTransaction_price());
            preparedStatement.setInt(4,toAdd.getAccount_id());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return toAdd;
    }

    @Override
    public Transaction update(Transaction toUpdate) {
        try {
            String updateQuery = "UPDATE transaction SET transaction_date=?,transaction_type=?,transaction_price=?,account_id=? WHERE id=?";
            PreparedStatement updateStatement = conn.prepareStatement(updateQuery);
            updateStatement.setObject(1, toUpdate.getTransaction_date());
            updateStatement.setString(2, toUpdate.getTransaction_type());
            updateStatement.setInt(3, toUpdate.getTransaction_price());
            updateStatement.setInt(4,toUpdate.getAccount_id());
            updateStatement.setInt(5, toUpdate.getId());
            updateStatement.executeUpdate();

            String selectQuery = "SELECT * FROM transaction WHERE id=?";
            PreparedStatement selectStatement = conn.prepareStatement(selectQuery);
            selectStatement.setInt(1, toUpdate.getId());

            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                Transaction updatedTransaction = new Transaction();
                updatedTransaction.setId(resultSet.getInt("id"));
                updatedTransaction.setTransaction_date(resultSet.getDate("transaction_date").toLocalDate());
                updatedTransaction.setTransaction_type(resultSet.getString("transaction_type"));
                updatedTransaction.setTransaction_price(resultSet.getInt("transaction_price"));
                updatedTransaction.setAccount_id(resultSet.getInt("account_id"));


                System.out.println("transaction updated");
                return updatedTransaction;
            } else {
                throw new RuntimeException("Failed to retrieve updated transaction information.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Transaction delete(Transaction toDelete) {
        try {
            String query = "DELETE FROM transaction WHERE id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, toDelete.getId());
            preparedStatement.executeUpdate();

            System.out.println("transaction deleted");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return toDelete;
    }

    @Override
    public Transaction getOne(Transaction one) throws PropertyNotFoundException {
        return null;
    }
}
