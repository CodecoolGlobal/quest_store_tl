package com.codecool.quest_store.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.time.LocalDate;

import com.codecool.quest_store.model.Transaction;

public class TransactionDaoImpl implements TransactionDao {

    private String query;

    @Override
    public void createTransaction(Transaction transaction) throws DaoException {
        String timestamp = LocalDate.now().toString();
        query = "INSERT INTO transactions (id_funding, id_user, id_team, id_item, id_status, timestamp, paid_amount) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, transaction.getIdFunding());
            preparedStatement.setInt(2, transaction.getUserId());
            preparedStatement.setInt(3, transaction.getTeamId());
            preparedStatement.setInt(4, transaction.getItemId());
            preparedStatement.setInt(5, transaction.getStatusId());
            preparedStatement.setString(6, timestamp);
            preparedStatement.setInt(7, transaction.getPaidAmount());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("Failed to create a transaction\n" + e);
        }
    }

    /**
     *
     * @param column (id_user or id_team)
     * @param columnId (id of user or id of team)
     */

    @Override
    public void updateTransactionStatus(String column, int columnId, int status, int idFunding) throws DaoException {

        query = "UPDATE transactions "
                + "SET id_status = ? "
                + "WHERE " + column + " = ? AND id_funding = ?";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, status);
            preparedStatement.setInt(2, columnId);
            preparedStatement.setInt(3, idFunding);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("Failed to update the transaction's status\n" + e);
        }
    }

    @Override
    public int getPriceSumOfRealizedQuests(int userId) throws DaoException {
        query = "SELECT SUM(price) FROM items "
                + "INNER JOIN transactions "
                + "ON transactions.id_item = items.id "
                + "INNER JOIN item_types "
                + "ON items.item_type = item_types.id "
                + "INNER JOIN statuses "
                + "ON transactions.id_status = statuses.id "
                + "WHERE transactions.id_user = ? "
                + "AND item_types.type='Quest' AND statuses.type='Realized'";

        return getSumOfPrices(userId);
    }

    @Override
    public int getPriceSumOfPurchasedArtifacts(int userId) throws DaoException {
        query = "SELECT SUM(price) FROM items "
                + "INNER JOIN transactions "
                + "ON transactions.id_item = items.id "
                + "INNER JOIN item_types "
                + "ON items.item_type = item_types.id "
                + "INNER JOIN statuses "
                + "ON transactions.id_status = statuses.id "
                + "WHERE transactions.id_user = ? "
                + "AND item_types.type='Artifact' "
                + "AND (statuses.type='Realized' OR statuses.type='Pending')";

        return getSumOfPrices(userId);
    }

    private int getSumOfPrices(int userId) throws DaoException {
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