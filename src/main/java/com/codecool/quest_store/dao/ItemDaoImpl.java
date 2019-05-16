package com.codecool.quest_store.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.codecool.quest_store.model.Item;

public class ItemDaoImpl implements ItemDao {

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
            type = resultSet.getInt("item_type");
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

    public List<Item> getAllItems() throws DaoException{
        String query =
                "SELECT * FROM items";
//                "SELECT * FROM items WHERE item_type = 1 OR item_type = 2;";
        List<Item> allArtifacts;

        try (Connection connection = DatabaseConnector.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery()){
                allArtifacts = getListByResultSet(rs);
        } catch (SQLException e){
            throw new DaoException("Failed to get all items\n", e);
        }
        return allArtifacts;
    }

    public Item getItemById(int itemId) throws DaoException{
        String query =
                "SELECT * FROM items WHERE id=?;";
        try(Connection connection = DatabaseConnector.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(query)){
            pstmt.setInt(1, itemId);
            try{
                ResultSet rs = pstmt.executeQuery();
                return getListByResultSet(rs).get(0);
            } catch (SQLException e){
                throw new DaoException("Failed to get item by id", e);
            }
        } catch (SQLException e){
            throw new DaoException("Failed to get item by id", e);
        }
    }

    @Override
    public int getDiscount() throws DaoException {
        String query =
                "SELECT discount FROM discount;";

        try(Connection connection = DatabaseConnector.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery()){
            rs.next();
            return rs.getInt("discount");
        } catch (SQLException e){
            throw new DaoException("Failed to get discount", e);
        }
    }

    @Override
    public void setDiscount(int newDiscount) throws DaoException {
        String query =
                "UPDATE discount SET discount = ?;";

        try(Connection connection = DatabaseConnector.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(query)){
            pstmt.setInt(1, newDiscount);
            pstmt.executeUpdate();
        } catch (SQLException e){
            throw new DaoException("Failed to update new discount", e);
        }
    }

    public List<Item> getUserItems(int user_id) throws DaoException{
        String query = "SELECT * FROM items " +
                "INNER JOIN fundings ON items.id = fundings.item_id " +
                "INNER JOIN transactions ON transactions.funding_id = fundings.id " +
                "INNER JOIN status_history ON status_history.funding_id = fundings.id " +
                "INNER JOIN statuses ON statuses.id = status_history.status_id " +
                "WHERE transactions.user_id = ? AND statuses.type = 'Pending';";
        try(Connection connection = DatabaseConnector.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(query)){
            pstmt.setInt(1, user_id);
            ResultSet rs = pstmt.executeQuery();

            return getListByResultSet(rs);

        } catch (SQLException e){
            throw new DaoException("Failed to get item by id", e);
        }
    }

    public static void main(String[] args) {
        ItemDaoImpl dao = new ItemDaoImpl();
        try {
            for(Item item : dao.getUserItems(1)) {
                System.out.println(item.getType());
            }
        } catch(DaoException error) {
            error.printStackTrace();
        }
    }
}