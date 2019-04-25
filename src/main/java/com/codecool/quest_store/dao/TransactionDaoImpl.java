package com.codecool.quest_store.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.time.LocalDate;

import com.codecool.quest_store.model.Codecooler;
import com.codecool.quest_store.model.Item;

public class TransactionDaoImpl implements TransactionDao {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private String query;

    @Override
    public void createTransaction(Codecooler codecooler, Item item, int status, int coinsAmount) {
        connection = DatabaseConnector.getConnection();

        query = "INSERT INTO transactions (id_user, id_team, id_item, id_status, timestamp, coins_amount) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        String timestamp = LocalDate.now().toString();

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, codecooler.getId());
            preparedStatement.setInt(2, codecooler.getTeamId());
            preparedStatement.setInt(3, item.getId());
            preparedStatement.setInt(4, status);
            preparedStatement.setString(5, timestamp);
            preparedStatement.setInt(6, coinsAmount);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param column (id_user or id_team)
     */

    @Override
    public void updateTransactionStatus(String column, int status, int idFunding) {
        connection = DatabaseConnector.getConnection();

        query = "UPDATE transactions "
                + "SET id_status = ? "
                + "WHERE " + column + " AND id_funding = ?";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, status);
            preparedStatement.setInt(2, idFunding);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getPriceSumOfRealizedQuests(int userId) {
        query = "SELECT SUM(price) FROM items "
                + "INNER JOIN transactions "
                + "ON transactions.id_item = items.id "
                + "INNER JOIN item_types "
                + "ON items.id = item_types.id "
                + "INNER JOIN statuses "
                + "ON transactions.id_status = statuses.id "
                + "WHERE transactions.id_user = ? "
                + "AND item_types.type='Quest' AND statuses.type='Realized'";

        return getSumOfPrices(userId);
    }

    @Override
    public int getPriceSumOfPurchasedArtifacts(int userId) {
        query = "SELECT SUM(price) FROM items "
                + "INNER JOIN transactions "
                + "ON transactions.id_item = items.id "
                + "INNER JOIN item_types "
                + "ON items.id = item_types.id "
                + "INNER JOIN statuses "
                + "ON transactions.id_status = statuses.id "
                + "WHERE transactions.id_user = ? "
                + "AND item_types.type='Artifact' "
                + "AND (statuses.type='Realized' OR statuses.type='Pending')";

        return getSumOfPrices(userId);
    }

    private int getSumOfPrices(int userId) {
        connection = DatabaseConnector.getConnection();
        int sumOfPrices = 0;

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                sumOfPrices = ((Number) resultSet.getObject(1)).intValue();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sumOfPrices;
    }
}