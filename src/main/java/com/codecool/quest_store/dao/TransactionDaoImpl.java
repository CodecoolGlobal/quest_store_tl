package com.codecool.quest_store.dao;

import java.sql.*;

import java.time.OffsetDateTime;

import com.codecool.quest_store.model.Transaction;
import com.codecool.quest_store.model.User;

public class TransactionDaoImpl implements TransactionDao, Dao<Transaction> {

    @Override
    public void create(Transaction transaction) throws DaoException {
        String query = "INSERT INTO transactions (funding_id, user_id, timestamp, paid_amount) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, transaction.getFUNDING_ID());
            preparedStatement.setInt(2, transaction.getUSER_ID());
            preparedStatement.setObject(3, transaction.getTIMESTAMP(), Types.TIMESTAMP_WITH_TIMEZONE);
            preparedStatement.setInt(4, transaction.getPAID_AMOUNT());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("Failed to create a transaction\n" + e);
        }
    }

    @Override
    public void update(Transaction transaction) throws DaoException {
        String query = "UPDATE transactions "
                + "SET funding_id = ?, "
                + "user_id = ?, "
                + "timestamp = ?, "
                + "paid_amount = ? "
                + "WHERE id = ?";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, transaction.getFUNDING_ID());
            preparedStatement.setInt(2, transaction.getUSER_ID());
            preparedStatement.setObject(3, transaction.getTIMESTAMP());
            preparedStatement.setInt(4, transaction.getPAID_AMOUNT());
            preparedStatement.setInt(5, transaction.getID());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("Failed to update the transaction's status\n" + e);
        }
    }

    @Override
    public Transaction extractFromResultSet(ResultSet resultSet) throws DaoException {
        int ID;
        int FUNDING_ID;
        int USER_ID;
        OffsetDateTime TIMESTAMP;
        int PAID_AMOUNT;
        Transaction transaction;

        try {
            ID = resultSet.getInt("id");
            FUNDING_ID = resultSet.getInt("funding_id");
            USER_ID = resultSet.getInt("user_id");
            TIMESTAMP = resultSet.getObject("timestamp", OffsetDateTime.class);
            PAID_AMOUNT = resultSet.getInt("paid_amount");
        } catch (SQLException e) {
            throw new DaoException("Failed to get transaction from result set\n" + e);
        }

        transaction = new Transaction.Builder()
                .withID(ID)
                .withFUNDING_ID(FUNDING_ID)
                .withUSER_ID(USER_ID)
                .withTIMESTAMP(TIMESTAMP)
                .withPAID_AMOUNT(PAID_AMOUNT)
                .build();

        return transaction;
    }

    @Override
    public int getPriceSumOfRealizedQuests(User user) throws DaoException {
        int userId = user.getId();
        String query = "SELECT SUM(paid_amount) FROM transactions "
                + "INNER JOIN fundings "
                + "ON transactions.funding_id = fundings.id "
                + "INNER JOIN status_history "
                + "ON fundings.id = status_history.funding_id "
                + "INNER JOIN items "
                + "ON fundings.item_id = items.id "
                + "WHERE transactions.user_id = ? "
                + "AND items.item_type IN (3,4) AND status_history.status_id != 4;";
        return getSumOfPrices(userId, query);
    }

    @Override
    public int getPriceSumOfPurchasedArtifacts(User user) throws DaoException {
        int userId = user.getId();
        String query = "SELECT SUM(paid_amount) FROM transactions "
                + "INNER JOIN fundings "
                + "ON transactions.funding_id = fundings.id "
                + "INNER JOIN status_history "
                + "ON fundings.id = status_history.funding_id "
                + "INNER JOIN items "
                + "ON fundings.item_id = items.id "
                + "WHERE transactions.user_id = ? "
                + "AND items.item_type IN (1,2) AND status_history.status_id != 4;";
        return getSumOfPrices(userId, query);
    }

    private int getSumOfPrices(int userId, String query) throws DaoException {
        int sumOfPrices = 0;

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {
                    System.out.println("calc1");
                    sumOfPrices = resultSet.getInt("sum");
                    System.out.println("calc2");
                }
            }

        } catch (SQLException e) {
            throw new DaoException("Failed to calculate the price sum of items\n" + e);
        }
        return sumOfPrices;
    }


}