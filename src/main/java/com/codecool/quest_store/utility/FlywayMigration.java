package com.codecool.quest_store.utility;

import com.codecool.quest_store.dao.DatabaseConnector;
import org.flywaydb.core.Flyway;

import javax.sql.DataSource;

public class FlywayMigration {

    public static void migrateDatabase(){

        DataSource dataSource = DatabaseConnector.getDs();
        Flyway flyway = Flyway.configure().dataSource(dataSource).load();

        flyway.migrate();
        System.out.println("Flyway: database migration complete");

    }
}
