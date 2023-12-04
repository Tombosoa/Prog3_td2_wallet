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
            String query = "SELECT id, devise_name, devise_country from devise ";
            try (ResultSet result = statement.executeQuery(query)) {
                while (result.next()) {
                    int id = result.getInt("id");
                    String devise_name = result.getString("devise_name");
                    String devise_country = result.getString("devise_country");

                    Devise devise = new Devise (id,devise_name, devise_country);
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
                String query = "INSERT INTO devise (devise_name,devise_country) VALUES ( ?, ?)";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, devise.getDevise_name());
                preparedStatement.setString(2, devise.getDevise_country());


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
            String query = "INSERT INTO devise (devise_name, devise_country) VALUES ( ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, toAdd.getDevise_name());
            preparedStatement.setString(2, toAdd.getDevise_country());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return toAdd;
    }

    @Override
    public Devise update(Devise toUpdate) {
        try {
            String updateQuery = "UPDATE devise SET devise_name=? , devise_country=? WHERE id=?";
            PreparedStatement updateStatement = conn.prepareStatement(updateQuery);
            updateStatement.setString(1, toUpdate.getDevise_name());
            updateStatement.setString(2,toUpdate.getDevise_country());
            updateStatement.setInt(3, toUpdate.getId());
            updateStatement.executeUpdate();

            String selectQuery = "SELECT * FROM devise WHERE id=?";
            PreparedStatement selectStatement = conn.prepareStatement(selectQuery);
            selectStatement.setInt(1, toUpdate.getId());

            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                Devise updatedDevise = new Devise();
                updatedDevise.setId(resultSet.getInt("id"));
                updatedDevise.setDevise_name(resultSet.getString("devise_name"));
                updatedDevise.setDevise_country(resultSet.getString("devise_country"));


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
            String query = "DELETE FROM devise WHERE id = ?";
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
            String query = "DELETE FROM devise where id = ?";
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
            String query = "select * from devise where id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                Devise devise = new Devise();
                devise.setId(resultSet.getInt("id"));
                devise.setDevise_name(resultSet.getString("devise_name"));
                devise.setDevise_country(resultSet.getString("devise_country"));

                return devise;
            }else{
                throw new PropertyNotFoundException("devise not found");
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

}
