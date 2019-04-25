package com.codecool.quest_store.dao;

import com.codecool.quest_store.model.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RoomDaoImpl implements RoomDao {

    @Override
    public void createRoom(Room room) {

        String SQL = "INSERT INTO rooms (name) VALUES (?)";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(SQL)) {
            pstmt.setString(1, room.getRoomName());

            System.out.println("added " + room.getRoomName() + " to rooms");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
