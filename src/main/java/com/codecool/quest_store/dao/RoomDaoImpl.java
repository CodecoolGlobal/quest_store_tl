package com.codecool.quest_store.dao;

import java.sql.*;
import java.util.Map;

import com.codecool.quest_store.model.Room;

public class RoomDaoImpl implements Dao<Room> {

    @Override
    public void create(Room room) throws DaoException {
        String SQL = "INSERT INTO rooms (name) VALUES (?)";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(SQL)) {
            pstmt.setString(1, room.getRoomName());
            pstmt.executeUpdate();
        } catch (SQLException e){
            throw new DaoException("failed to create room " + room.getRoomName(), e);
        }

    }

    @Override
    public void update(Room room) throws DaoException {

        int id = room.getId();
        String newName = room.getRoomName();

        String SQL =
                "UPDATE rooms SET name = ? WHERE id = ?;";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(SQL)){
            pstmt.setString(1, newName);
            pstmt.setInt(2, id);
            pstmt.executeUpdate(SQL);
        } catch (SQLException e){
            throw new DaoException("failed to update room " + newName, e);
        }
    }

    @Override
    public Room extractFromResultSet(ResultSet resultSet) throws DaoException {
        try{
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");

            return new Room.RoomBuilder()
                    .withId(id)
                    .withRoomName(name)
                    .build();
        } catch (SQLException e){
            throw new DaoException("failed to extract team from result set", e);
        }
    }
}
