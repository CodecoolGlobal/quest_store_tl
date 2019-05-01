package com.codecool.quest_store.dao;

import com.codecool.quest_store.model.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class RoomDaoImpl implements Dao<Room> {

    @Override
    public void create(Room thing) throws DaoException {
        String SQL = "INSERT INTO rooms (name) VALUES (?)";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(SQL)) {
            pstmt.setString(1, thing.getRoomName());
            pstmt.executeUpdate();
        } catch (SQLException e){
            throw new DaoException("failed to create room " + thing.getRoomName(), e);
        }

    }

    @Override
    public void update(Room thing) throws DaoException {

        int id = thing.getId();
        String newName = thing.getRoomName();

        String SQL =
                "UPDATE rooms SET name = " + newName
                + " WHERE id = " + id;

        try (Connection connection = DatabaseConnector.getConnection();
             Statement stmt = connection.createStatement()){
            stmt.executeUpdate(SQL);
        } catch (SQLException e){
            throw new DaoException("failed to update room " + newName, e);
        }
    }
}
