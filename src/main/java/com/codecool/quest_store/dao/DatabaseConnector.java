package com.codecool.quest_store.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

    private static final String dbUrl = "jdbc:postgresql://localhost:5432/queststore"; //TODO:config file needed
    private static final String user = "marek";
    private static final String password = "polapola";

    private static Connection connectionInstance = null;


    private DatabaseConnector() {
    }

    public static Connection getConnection() {
        try {
            if (connectionInstance == null) {
                connectionInstance = DriverManager.getConnection(dbUrl, user, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connectionInstance;
    }

    public static void releaseConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}