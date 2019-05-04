package com.codecool.quest_store.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.time.LocalDate;

import com.codecool.quest_store.model.Transaction;

public class TransactionDaoImpl implements TransactionDao, Dao<Transaction> {

    @Override
    public void create(Transaction transaction) throws DaoException {
        String query = "INSERT INTO transactions (funding_id, user_id, timestamp, paid_amount) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, transaction.getFUNDING_ID());
            preparedStatement.setInt(2, transaction.getUSER_ID());
            preparedStatement.setDate(3, Date.valueOf(transaction.getTIMESTAMP()));
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
            preparedStatement.setDate(3, Date.valueOf(transaction.getTIMESTAMP()));
            preparedStatement.setInt(4, transaction.getPAID_AMOUNT());
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
        LocalDate TIMESTAMP;
        int PAID_AMOUNT;
        Transaction transaction;

        try {
            ID = resultSet.getInt("id");
            FUNDING_ID = resultSet.getInt("funding_id");
            USER_ID = resultSet.getInt("user_id");
            TIMESTAMP = resultSet.getDate("timestamp").toLocalDate();
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
    public int getPriceSumOfRealizedQuests(Transaction transaction) throws DaoException {
        int userId = transaction.getUSER_ID();
        String query = "SELECT SUM(price) FROM items "
                + "INNER JOIN transactions "
                + "ON transactions.id_item = items.id "
                + "INNER JOIN item_types "
                + "ON items.item_type = item_types.id "
                + "INNER JOIN statuses "
                + "ON transactions.id_status = statuses.id "
                + "WHERE transactions.id_user = ? "
                + "AND item_types.type='Quest' AND statuses.type='Realized'";
        return getSumOfPrices(userId, query);
    }

    @Override
    public int getPriceSumOfPurchasedArtifacts(Transaction transaction) throws DaoException {
        int userId = transaction.getUSER_ID();
        String query = "SELECT SUM(price) FROM items "
                + "INNER JOIN transactions "
                + "ON transactions.id_item = items.id "
                + "INNER JOIN item_types "
                + "ON items.item_type = item_types.id "
                + "INNER JOIN statuses "
                + "ON transactions.id_status = statuses.id "
                + "WHERE transactions.id_user = ? "
                + "AND item_types.type='Artifact' "
                + "AND (statuses.type='Realized' OR statuses.type='Pending' OR statuses.type='In progress')";
        return getSumOfPrices(userId, query);
    }

    private int getSumOfPrices(int userId, String query) throws DaoException {
        int sumOfPrices = 0;

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {
                    sumOfPrices = ((Number) resultSet.getObject(1)).intValue();
                }
            }

        } catch (SQLException e) {
            throw new DaoException("Failed to calculate the price sum of items\n" + e);
        }
        return sumOfPrices;
    }
}