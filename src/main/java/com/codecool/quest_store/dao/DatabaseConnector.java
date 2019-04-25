package com.codecool.quest_store.dao;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

class DatabaseConnector {

    private static final String URL = "jdbc:postgresql://localhost:5432/queststore"; //TODO:config file needed
    private static final String USER = "marek";
    private static final String PASSWORD = "polapola";

    private static BasicDataSource ds = new BasicDataSource();

    private DatabaseConnector(){ }

    static {
        ds.setUrl(URL);
        ds.setUsername(USER);
        ds.setPassword(PASSWORD);
        ds.setMinIdle(5);
        ds.setMaxIdle(10);
        ds.setMaxOpenPreparedStatements(100);
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}