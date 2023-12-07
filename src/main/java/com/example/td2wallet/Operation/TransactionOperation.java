package com.example.td2wallet.Operation;

import com.example.td2wallet.DataBaseConnection;
import com.example.td2wallet.Entity.Devise;
import com.example.td2wallet.Entity.Transaction;
import jakarta.el.PropertyNotFoundException;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
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
                    String transaction_type = result.getString("type");
                    double transaction_price = result.getDouble("amount");
                    int account_id = result.getInt("account_id");
                    String label = result.getString("label");

                    Transaction transaction = new Transaction(id,transaction_date,transaction_type,transaction_price,account_id, label);
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
                String query = "INSERT INTO transaction (transaction_date,type,amount,account_id, label) VALUES ( ?, ?,?,?, ?)";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setObject(1, transaction.getTransaction_date());
                preparedStatement.setString(2, transaction.getType());
                preparedStatement.setDouble(3, transaction.getAmount());
                preparedStatement.setInt(4,transaction.getAccount_id());
                preparedStatement.setString(5, transaction.getLabel());


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
            String query = "INSERT INTO transaction (type,amount,account_id, label) VALUES ( ?, ?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query);

            preparedStatement.setString(1, toAdd.getType());
            preparedStatement.setDouble(2, toAdd.getAmount());
            preparedStatement.setInt(3,toAdd.getAccount_id());
            preparedStatement.setString(4, toAdd.getLabel());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return toAdd;
    }

    @Override
    public Transaction update(Transaction toUpdate) {
        try {
            String updateQuery = "UPDATE transaction SET transaction_date=?,type=?,amount=?,account_id=?, label= ? WHERE id=?";
            PreparedStatement updateStatement = conn.prepareStatement(updateQuery);
            updateStatement.setObject(1, toUpdate.getTransaction_date());
            updateStatement.setString(2, toUpdate.getType());
            updateStatement.setDouble(3, toUpdate.getAmount());
            updateStatement.setInt(4,toUpdate.getAccount_id());
            updateStatement.setString(5, toUpdate.getLabel());
            updateStatement.setInt(6, toUpdate.getId());
            updateStatement.executeUpdate();

            String selectQuery = "SELECT * FROM transaction WHERE id=?";
            PreparedStatement selectStatement = conn.prepareStatement(selectQuery);
            selectStatement.setInt(1, toUpdate.getId());

            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                Transaction updatedTransaction = new Transaction();
                updatedTransaction.setId(resultSet.getInt("id"));
                updatedTransaction.setTransaction_date(resultSet.getDate("transaction_date").toLocalDate());
                updatedTransaction.setType(resultSet.getString("type"));
                updatedTransaction.setAmount(resultSet.getInt("amount"));
                updatedTransaction.setAccount_id(resultSet.getInt("account_id"));
                updatedTransaction.setLabel(resultSet.getString("label"));


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

    public Transaction getOne(int id) throws PropertyNotFoundException{
        try {
            String query = "select * from transaction where id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                Transaction transaction = new Transaction();
                transaction.setId(resultSet.getInt("id"));
                transaction.setAmount(resultSet.getInt("amount"));
                transaction.setType(resultSet.getString("type"));
                transaction.setTransaction_date(resultSet.getDate("transaction_date").toLocalDate());
                transaction.setAccount_id(resultSet.getInt("account_id"));
                transaction.setLabel(resultSet.getString("label"));

                return transaction;
            }else{
                throw new PropertyNotFoundException("transaction not found");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteTransaction(int id){
        try {
            String query = "DELETE FROM transaction where id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeQuery();

            System.out.println("transaction deleted");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
