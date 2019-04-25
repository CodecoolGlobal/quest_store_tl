package com.codecool.quest_store.dao;

import com.codecool.quest_store.utility.ConfigFileParser;
import org.apache.commons.dbcp.BasicDataSource;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

class DatabaseConnector {

    private static BasicDataSource ds = new BasicDataSource();


    private DatabaseConnector(){ }

    static {
        try{
            final String URL = ConfigFileParser.getDatabaseURL(); //TODO:config file needed
            final String USER = ConfigFileParser.getDatabaseUser();
            final String PASSWORD = ConfigFileParser.getDatabasePassword();
            ds.setUrl(URL);
            ds.setUsername(USER);
            ds.setPassword(PASSWORD);

        } catch (IOException | ParseException e) {
            System.out.println("Config file connection failed");
            e.printStackTrace();
        }

        ds.setMinIdle(5);
        ds.setMaxIdle(10);
        ds.setMaxOpenPreparedStatements(100);
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}