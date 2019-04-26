package com.codecool.quest_store.main;


import com.codecool.quest_store.utility.FlywayMigration;

public class Main {
    public static void main( String[] args ) {
        FlywayMigration.migrateDatabase();
    }
}

// import com.codecool.quest_store.dao.TransactionDaoImpl;

// public class Main {
//     public static void main( String[] args ) {
//         TransactionDaoImpl dao = new TransactionDaoImpl();
//         System.out.println(dao.getPriceSumOfPurchasedArtifacts(3));
//     }
// }
