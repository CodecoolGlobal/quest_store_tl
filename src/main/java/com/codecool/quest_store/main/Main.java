package com.codecool.quest_store.main;

import com.codecool.quest_store.utility.FlywayMigration;

public class Main {
    public static void main( String[] args ) {
        FlywayMigration.migrateDatabase();
    }
}