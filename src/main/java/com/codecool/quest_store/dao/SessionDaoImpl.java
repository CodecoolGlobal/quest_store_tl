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

    public void updateSession(int session, int userId) throws DaoException {
        String query = "UPDATE sessions SET session = ? WHERE user_id = ?;";
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, session);
            statement.setInt(2, userId);
            statement.executeUpdate();
        }  catch(SQLException error) {
            throw new DaoException("It's impossible to update this session");
        }
    }

    public Integer getSession(int userId) throws DaoException {
        String query = "SELECT * FROM sessions WHERE user_id = ?";
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    int session = rs.getInt("session");
                    System.out.println(session);
                    return session;
                }
            }
        } catch (SQLException error) {
            throw new DaoException("It's impossible to update this session");
        }
        return null;
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
            dao.getSession(1);
        } catch(DaoException error) {
            error.printStackTrace();
        }
    }
}
