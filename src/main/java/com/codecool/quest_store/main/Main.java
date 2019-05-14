package com.codecool.quest_store.main;

import com.codecool.quest_store.controllers.LoginController;
import com.codecool.quest_store.controllers.Static;
import com.codecool.quest_store.utility.FlywayMigration;
import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;

public class Main {
    public static void main( String[] args ) throws Exception {
        //FlywayMigration.migrateDatabase();
        ////http://localhost:8000/login
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        server.createContext("/static", new Static());
        server.createContext("/login", new LoginController());
        server.setExecutor(null);

        server.start();
    }
}