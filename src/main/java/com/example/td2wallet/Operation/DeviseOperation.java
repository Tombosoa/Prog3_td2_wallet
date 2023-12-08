package com.example.td2wallet.Operation;

import com.example.td2wallet.DataBaseConnection;
import com.example.td2wallet.Entity.Account;
import com.example.td2wallet.Entity.Devise;
import com.example.td2wallet.Entity.User;
import jakarta.el.PropertyNotFoundException;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Component
public class DeviseOperation implements CrudOperation<Devise> {

    String userName = System.getenv("DB_USERNAME");
    String password = System.getenv("DB_PASSWORD");
    String databaseName = System.getenv("DB_NAME");
    DataBaseConnection dbConnection = new DataBaseConnection(userName, password, databaseName);
    Connection conn = dbConnection.getConnection();
    public Statement statement;
    @Override
    public List<Devise> findAll() {
        List<Devise> deviseList = new ArrayList<>();
        try (Statement statement = conn.createStatement()) {
            String query = "SELECT id, name, code from currency ";
            try (ResultSet result = statement.executeQuery(query)) {
                while (result.next()) {
                    int id = result.getInt("id"); // cela s'appele des magic numbers
                    String devise_name = result.getString("name");
                    String devise_code = result.getString("code");

                    Devise devise = new Devise (id,devise_name, devise_code);
                    deviseList.add(devise);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching devise from the database", e);
        }
        return deviseList;
    }

    @Override
    public List<Devise> saveAll(List<Devise> toSave) {
        List<Devise> savedDevises = new ArrayList<>();
        try {
            for (Devise devise : toSave) {
                String query = "INSERT INTO currency (name,code) VALUES ( ?, ?)";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, devise.getName());
                preparedStatement.setString(2, devise.getCode());


                preparedStatement.executeUpdate();
                savedDevises.add(devise);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return savedDevises;
    }

    @Override
    public Devise save(Devise toAdd) {
        try {
            String query = "INSERT INTO currency (name, code) VALUES ( ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, toAdd.getName());
            preparedStatement.setString(2, toAdd.getCode());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return toAdd;
    }

    @Override
    public Devise update(Devise toUpdate) {
        try {
            String updateQuery = "UPDATE currency SET name=? , code=? WHERE id=?";
            PreparedStatement updateStatement = conn.prepareStatement(updateQuery);
            updateStatement.setString(1, toUpdate.getName());
            updateStatement.setString(2,toUpdate.getCode());
            updateStatement.setInt(3, toUpdate.getId());
            updateStatement.executeUpdate();

            String selectQuery = "SELECT * FROM currency WHERE id=?";
            PreparedStatement selectStatement = conn.prepareStatement(selectQuery);
            selectStatement.setInt(1, toUpdate.getId());

            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                Devise updatedDevise = new Devise();
                updatedDevise.setId(resultSet.getInt("id"));
                updatedDevise.setName(resultSet.getString("name"));
                updatedDevise.setCode(resultSet.getString("code"));


                System.out.println("Devise updated");
                return updatedDevise;
            } else {
                throw new RuntimeException("Failed to retrieve updated devise information.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Devise delete(Devise toDelete) {
        try {
            String query = "DELETE FROM currency WHERE id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, toDelete.getId());
            preparedStatement.executeUpdate();

            System.out.println("devise deleted");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return toDelete;
    }

    public void deleteDevise(int id){
        try{
            String query = "DELETE FROM currency where id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeQuery();

            System.out.println("devise deleted");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Devise getOne(Devise one) throws PropertyNotFoundException {
        return null;
    }

    public Devise getOne(int id) throws PropertyNotFoundException{
        try{
            String query = "select * from currency where id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                Devise devise = new Devise();
                devise.setId(resultSet.getInt("id"));
                devise.setName(resultSet.getString("name"));
                devise.setCode(resultSet.getString("code"));

                return devise;
            }else{
                throw new PropertyNotFoundException("devise not found");
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

}
