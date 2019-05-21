package com.codecool.quest_store.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SessionDaoImpl {

    public void createSession(int session, int userId) throws DaoException {
        String query = "INSERT INTO sessions(session, user_id) VALUES(?, ?);";
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, session);
            statement.setInt(2, userId);
            statement.executeUpdate();
        }  catch(SQLException error) {
            throw new DaoException("It's impossible to create a new session");
        }
    }

    public void deleteSession(int session) throws DaoException {
        String query = "DELETE FROM sessions WHERE session = ?;";
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, session);
            statement.executeUpdate();
        } catch(SQLException error) {
            throw new DaoException("It's impossible to update this session");
        }
    }

    public Integer getUserId(int session) throws DaoException {
        String query = "SELECT user_id FROM sessions WHERE session = ?";
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, session);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    int user_id = rs.getInt("user_id");
                    System.out.println(session);
                    return user_id;
                }
            }
        } catch (SQLException error) {
            throw new DaoException("It's impossible to update this session");
        }
        return null;
    }

    public static void main(String[] args) {
        SessionDaoImpl dao = new SessionDaoImpl();
        try {
            dao.deleteSession(-525031425);
        } catch(DaoException error) {
            error.printStackTrace();
        }
    }
}
