package com.codecool.quest_store.dao;

import com.codecool.quest_store.model.Room;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RoomDAOImpl implements RoomDAO {

    private Connection connection;
    private PreparedStatement pstmt;


    @Override
    public void createRoom(Room room) {
        connection = DatabaseConnector.getConnection();

        try {
            pstmt = connection.prepareStatement(
                    "INSERT INTO rooms (name) VALUES (?);"
            );
            pstmt.setString(1, room.getRoomName());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
