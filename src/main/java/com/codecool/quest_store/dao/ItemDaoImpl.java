package com.codecool.quest_store.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.codecool.quest_store.model.Item;

public class ItemDaoImpl implements Dao<Item> {

    @Override
    public void create(Item item) throws DaoException {
        String query = "INSERT INTO items (price, title, description, item_type) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, item.getPrice());
            preparedStatement.setString(2, item.getTitle());
            preparedStatement.setString(3, item.getDescription());
            preparedStatement.setInt(4, item.getType());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("Failed to create an item\n" + e);
        }
    }

    @Override
    public void update(Item item) throws DaoException {
        String query = "UPDATE items "
                + "SET price = ?, "
                + "title = ?, "
                + "description = ?, "
                + "item_type = ? "
                + "WHERE id = ?";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, item.getPrice());
            preparedStatement.setString(2, item.getTitle());
            preparedStatement.setString(3, item.getDescription());
            preparedStatement.setInt(4, item.getType());
            preparedStatement.setInt(5, item.getID());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("Failed to update item\n" + e);
        }
    }

    @Override
    public Item extractFromResultSet(ResultSet resultSet) throws DaoException {
        int ID;
        int price;
        int type;
        String title;
        String description;
        Item item;

        try {
            ID = resultSet.getInt("id");
            price = resultSet.getInt("price");
            title = resultSet.getString("title");
            description = resultSet.getString("description");
            type = resultSet.getInt("type");
        } catch (SQLException e) {
            throw new DaoException("Failed to get item from result set\n" + e);
        }

        item = new Item.Builder()
                .withID(ID)
                .withPrice(price)
                .withTitle(title)
                .withDescription(description)
                .withType(type)
                .build();

        return item;
    }
}