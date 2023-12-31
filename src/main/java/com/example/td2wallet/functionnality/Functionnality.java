package com.example.td2wallet.functionnality;
import com.example.td2wallet.DataBaseConnection;
import com.example.td2wallet.Entity.*;
import jakarta.el.PropertyNotFoundException;
import org.springframework.stereotype.Component;


import java.math.BigDecimal;
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

    public Account makeTransaction(Transaction transaction) {
        try {
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
            if (transaction.getType().equals("Debit")) {
                newStatement.setDouble(1, survol.getSolde() - transaction.getAmount());
                newStatement.setInt(2, transaction.getAccount_id());
            } else if (transaction.getType().equals("Credit")) {
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
                return account;
            } else {
                throw new PropertyNotFoundException("Account not found");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public AccountDate getTodayBalance(int account_id) throws PropertyNotFoundException {
        try {
            String query = "SELECT transaction.id AS id, transaction_date, transaction.type as type, amount, account_id, label, solde, currency.name as name FROM account INNER JOIN transaction ON transaction.account_id = account.id INNER JOIN currency ON currency.id = account.currency_id WHERE account.id =? order by transaction_date desc limit 1";
            PreparedStatement preparedStatemente = conn.prepareStatement(query);

            preparedStatemente.setInt(1, account_id);

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
                return account;
            } else {
                throw new PropertyNotFoundException("Account not found");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public ResponseTransfer makeTransfer(double montant, int idCompteDeb, int idCompteCred) {
        try {
            Account accountDeb = getAccountById(idCompteDeb);
            Account accountCred = getAccountById(idCompteCred);
            int idTransactionDeb;
            int idTransactionCred;
            if (accountDeb.getCurrency_id() == accountCred.getCurrency_id()) {
                idTransactionDeb = makeTransactionAct(montant, "Debit", idCompteDeb);
                idTransactionCred = makeTransactionAct(montant, "Credit", idCompteCred);
            } else if (accountDeb.getCurrency_id() == 1 && accountCred.getCurrency_id() == 2) {
                double currencyToday = getCurrencyActual();
                double montantConverti = montant / currencyToday;

                idTransactionDeb = makeTransactionAct(montant, "Debit", idCompteDeb);
                idTransactionCred = makeTransactionAct(montantConverti, "Credit", idCompteCred);
            }else if(accountDeb.getCurrency_id() == 2 && accountCred.getCurrency_id() == 1){
                double currencyToday = getCurrencyActual();
                double montantConverti = montant * currencyToday;

                idTransactionDeb = makeTransactionAct(montant, "Debit", idCompteDeb);
                idTransactionCred = makeTransactionAct(montantConverti, "Credit", idCompteCred);
            } else {
                throw new RuntimeException("error");
            }

            insertTransferHistory(idTransactionDeb, idTransactionCred);

            ResponseTransfer responseTransfer = new ResponseTransfer(accountDeb, accountCred );

            return responseTransfer;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public int makeTransactionAct(double amount, String action, int account_id) {
        int idTransaction = 0;
        try {
            String query = "INSERT INTO transaction (type, amount, account_id, label) VALUES (?, ?, ?, ?)";
            PreparedStatement insertStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, action);
            insertStatement.setDouble(2, amount);
            insertStatement.setInt(3, account_id);
            insertStatement.setString(4, "make transfer");
            insertStatement.executeUpdate();

            ResultSet generatedKeys = insertStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                idTransaction = generatedKeys.getInt(1);

                updateAccountBalance(account_id, action, amount);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return idTransaction;
    }




    public NewResponseTransfer makeNewTransfer(double montant, int idCompte, int category_id, int subcategory_id, String action) {
        try {
            Account account = getAccountById(idCompte);

            int idTransaction;
            if (action.equals("Debit")) {
                idTransaction = makeNewTransactionAct(montant, "Debit", idCompte, category_id, subcategory_id);
                insertTransferHistory(idTransaction, idTransaction);
            } else if (action.equals("Credit")) {
                idTransaction = makeNewTransactionAct(montant, "Credit", idCompte, category_id, subcategory_id);
                insertTransferHistory(idTransaction, idTransaction);
            } else {
                throw new RuntimeException("error");
            }

            //insertTransferHistory(idTransactionDeb, idTransactionCred);

            NewResponseTransfer responseTransfer = new NewResponseTransfer(account ,montant);

            return responseTransfer;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public int makeNewTransactionAct(double amount, String action, int account_id, int category_id, int subcategory_id) {
        int idTransaction = 0;
        try {
            String query = "INSERT INTO transaction (type, amount, account_id, label, category_id, subcategory_id) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement insertStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, action);
            insertStatement.setDouble(2, amount);
            insertStatement.setInt(3, account_id);
            insertStatement.setString(4, "make transfer");
            insertStatement.setInt(5, category_id);
            insertStatement.setInt(6, subcategory_id);
            insertStatement.executeUpdate();

            ResultSet generatedKeys = insertStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                idTransaction = generatedKeys.getInt(1);

                updateAccountBalance(account_id, action, amount);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return idTransaction;
    }




    private void insertTransferHistory(int idTransactionDeb, int idTransactionCred) throws SQLException {
        String query = "INSERT INTO transferHistory (id_transactiondeb, id_transactioncred) VALUES (?, ?)";
        try (PreparedStatement statementTransfer = conn.prepareStatement(query)) {
            statementTransfer.setInt(1, idTransactionDeb);
            statementTransfer.setInt(2, idTransactionCred);
            statementTransfer.executeUpdate();
        }
    }

    private void updateAccountBalance(int account_id, String action, double amount) throws SQLException {
        Account account = getAccountById(account_id);

        String updateAccount = "UPDATE account SET solde = ? WHERE id = ?";
        try (PreparedStatement newStatement = conn.prepareStatement(updateAccount)) {
            if (action.equals("Debit")) {
                newStatement.setDouble(1, account.getSolde() - amount);
            } else if (action.equals("Credit")) {
                newStatement.setDouble(1, account.getSolde() + amount);
            }
            newStatement.setInt(2, account_id);
            newStatement.executeUpdate();
        }
    }

    private Account getAccountById(int account_id) throws SQLException {
        String selectAccount = "SELECT * FROM account WHERE id = ?";
        try (PreparedStatement selectStatement = conn.prepareStatement(selectAccount)) {
            selectStatement.setInt(1, account_id);

            try (ResultSet resultSet = selectStatement.executeQuery()) {
                if (resultSet.next()) {
                    Account account = new Account();
                    account.setAccount_id(resultSet.getInt("id"));
                    account.setName(resultSet.getString("name"));
                    account.setUser_id(String.valueOf(UUID.fromString(resultSet.getString("user_id"))));
                    account.setCurrency_id(resultSet.getInt("currency_id"));
                    account.setType(resultSet.getString("type"));
                    account.setSolde(resultSet.getDouble("solde"));
                    return account;
                } else {
                    throw new SQLException("Compte non trouvé avec l'ID : " + account_id);
                }
            }
        }
    }

    private double getCurrencyValueForToday() throws SQLException {
        double amount = 0;
        String sql = "SELECT amount FROM currencyvalue WHERE release_date = current_date";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    amount = resultSet.getDouble("amount");
                }
            }
        }
        return amount;
    }

    public double getCurrencyActual() throws  SQLException{
        double amount = 0;
        String sql = "select sum(amount)/count(amount) as current_value from currencyvalue where release_date::timestamp::date = current_date";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()){
                    amount = resultSet.getDouble("current_value");
                }
            }
        }
        return amount;
    }

    public double getCurrencyActualMax() throws  SQLException{
        double amount = 0;
        String sql = "select amount from currencyvalue where release_date::timestamp::date = current_date order by amount desc limit 1";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()){
                    amount = resultSet.getDouble("amount");
                }
            }
        }
        return amount;
    }

    public double getCurrencyActualMin() throws  SQLException{
        double amount = 0;
        String sql = "select amount from currencyvalue where release_date::timestamp::date = current_date order by amount asc limit 1";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()){
                    amount = resultSet.getDouble("amount");
                }
            }
        }
        return amount;
    }

    public double getAllCurrency(String action) throws SQLException {
        return switch (action) {
            case "max" -> getCurrencyActualMax();
            case "pond" -> getCurrencyActual();
            case "min" -> getCurrencyActualMin();
            default -> 0;
        };
    }

    public AccountHistory getTotalTransac(int account_id, String first_date, String last_date){
        try {
            String query = " SELECT \n" +
                    "    SUM(CASE WHEN type = 'Credit' THEN amount ELSE 0 END) AS credit,\n" +
                    "    SUM(CASE WHEN type = 'Debit' THEN amount ELSE 0 END) AS debit,\n" +
                    "    account_id\n" +
                    "FROM \n" +
                    "    transaction\n" +
                    "WHERE \n" +
                    "    account_id = ?\n" +
                    "    AND transaction_date BETWEEN ? AND ?\n" +
                    "GROUP BY \n" +
                    "    account_id;\n";
            PreparedStatement preparedStatemente = conn.prepareStatement(query);

            String firstdate = transformSpaceToT(first_date);
            String lastdate = transformSpaceToT(last_date);
            OffsetDateTime firstdateTime = OffsetDateTime.parse(firstdate);
            OffsetDateTime lastdateTime = OffsetDateTime.parse(lastdate);

            preparedStatemente.setInt(1, account_id);
            preparedStatemente.setTimestamp(2, Timestamp.from(firstdateTime.toInstant()));
            preparedStatemente.setTimestamp(3, Timestamp.from(lastdateTime.toInstant()));


            ResultSet resultSett = preparedStatemente.executeQuery();
            if (resultSett.next()) {
                int account_Id = resultSett.getInt("account_id");
                double debit = resultSett.getDouble("debit");
                double credit = resultSett.getDouble("credit");

                AccountHistory account = new AccountHistory(account_Id, credit,debit);
                return account;
            } else {
                throw new PropertyNotFoundException("History not found");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<SQLCresp> getTotalTransacCat(int account_id, String first_date, String last_date) {
        List<SQLCresp> resultList = new ArrayList<>();

        try {
            String query = "SELECT\n" +
                    "    c.category_name,\n" +
                    "    COALESCE(SUM(t.amount), 0) AS total_amount\n" +
                    "FROM\n" +
                    "    transaction t LEFT\n" +
                    "JOIN\n" +
                    "    category c ON t.category_id = c.id\n" +
                    "WHERE\n" +
                    "    t.transaction_date BETWEEN ? AND ?\n" +
                    "    AND t.account_id = ?\n" +
                    "GROUP BY\n" +
                    "    c.category_name;";
            PreparedStatement preparedStatement = conn.prepareStatement(query);

            String firstdate = transformSpaceToT(first_date);
            String lastdate = transformSpaceToT(last_date);
            OffsetDateTime firstdateTime = OffsetDateTime.parse(firstdate);
            OffsetDateTime lastdateTime = OffsetDateTime.parse(lastdate);

            preparedStatement.setTimestamp(1, Timestamp.from(firstdateTime.toInstant()));
            preparedStatement.setTimestamp(2, Timestamp.from(lastdateTime.toInstant()));
            preparedStatement.setInt(3, account_id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String category_name = resultSet.getString("category_name");
                BigDecimal total = BigDecimal.valueOf(resultSet.getDouble("total_amount"));

                SQLCresp account = new SQLCresp(category_name, total);
                resultList.add(account);
            }

            if (resultList.isEmpty()) {
                throw new PropertyNotFoundException("History not found");
            }

            return resultList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }




    public SQLresp executeFunction(int id_account, String datefirst, String datelast) throws SQLException {
        String sql = "SELECT * FROM gettotaltransac(?, ?, ?)";

        try (CallableStatement statement = conn.prepareCall(sql)) {
            String firstdate = transformSpaceToT(datefirst);
            String lastdate = transformSpaceToT(datelast);
            OffsetDateTime firstdateTime = OffsetDateTime.parse(firstdate);
            OffsetDateTime lastdateTime = OffsetDateTime.parse(lastdate);

            statement.setInt(1, id_account);
            statement.setTimestamp(2, Timestamp.from(firstdateTime.toInstant()));
            statement.setTimestamp(3, Timestamp.from(lastdateTime.toInstant()));


            ResultSet resultSet = statement.executeQuery();


            while (resultSet.next()) {
                BigDecimal credit = resultSet.getBigDecimal("credit");
                BigDecimal debit = resultSet.getBigDecimal("debit");
                int accountId = resultSet.getInt("account_id");

                //System.out.println("Credit: " + credit + ", Debit: " + debit + ", Account ID: " + accountId);
                SQLresp sqLresp = new SQLresp(accountId, debit, credit);
                return sqLresp;
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return null;
    }

    public List<SQLCresp> executeFunctionC(int id_account, String datefirst, String datelast) throws SQLException {
        List<SQLCresp> resultList = new ArrayList<>();
        String sql = "SELECT * FROM getnewtotaltransac(?, ?, ?)";

        try (CallableStatement statement = conn.prepareCall(sql)) {
            String firstdate = transformSpaceToT(datefirst);
            String lastdate = transformSpaceToT(datelast);
            OffsetDateTime firstdateTime = OffsetDateTime.parse(firstdate);
            OffsetDateTime lastdateTime = OffsetDateTime.parse(lastdate);

            statement.setInt(1, id_account);
            statement.setTimestamp(2, Timestamp.from(firstdateTime.toInstant()));
            statement.setTimestamp(3, Timestamp.from(lastdateTime.toInstant()));


            ResultSet resultSet = statement.executeQuery();


            while (resultSet.next()) {
                String category_name = resultSet.getString("category_name");
                BigDecimal total = BigDecimal.valueOf(resultSet.getDouble("total_amount"));

                SQLCresp account = new SQLCresp(category_name, total);

            resultList.add(account);
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return resultList;
    }

}
