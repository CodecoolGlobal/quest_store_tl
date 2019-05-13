package com.codecool.quest_store.main;


import com.codecool.quest_store.dao.Dao;
import com.codecool.quest_store.dao.DaoException;
import com.codecool.quest_store.dao.TeamDaoImpl;
import com.codecool.quest_store.model.Team;
import com.codecool.quest_store.utility.FlywayMigration;

public class Main {
    public static void main( String[] args ) {
//        FlywayMigration.migrateDatabase();

        Team fakeTeam = new Team.TeamBuilder()
                .withId(100)
                .withTeamName("fakeee team")
                .withProjectName("fakereee")
                .build();

        Dao<Team> teamDao = new TeamDaoImpl();
        try {
            teamDao.update(fakeTeam);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
}