package com.codecool.quest_store.dao;

import com.codecool.quest_store.model.Team;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class TeamDaoImpl implements Dao<Team> {

    @Override
    public void create(Team thing) throws DaoException {
        String newTeamName = thing.getTeamName();
        String newProjectName = thing.getProjectName();

        String SQL = "INSERT INTO teams (name, project_name) VALUES (?,?)";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(SQL)){
            pstmt.setString(1, newTeamName);
            pstmt.setString(2, newProjectName);
            pstmt.executeUpdate();
        } catch (SQLException e){
            throw new DaoException("failed to create team " + thing.getTeamName());
        }


    }

    @Override
    public void update(Team thing) throws DaoException {
        int id = thing.getId();
        String newTeamName = thing.getTeamName();
        String newProjectName = thing.getProjectName();

        String SQL = "UPDATE teams SET"
                + " name = " + newTeamName
                + " project_name = " + newProjectName
                + " WHERE id = " + id;

        try (Connection connection = DatabaseConnector.getConnection();
             Statement stmt = connection.createStatement()){
            stmt.executeUpdate(SQL);
        } catch (SQLException e){
            throw new DaoException("failed to update team " + newTeamName, e);
        }
    }
}
