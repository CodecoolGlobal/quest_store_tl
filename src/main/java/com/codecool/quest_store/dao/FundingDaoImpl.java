package com.codecool.quest_store.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.codecool.quest_store.model.Funding;

public class FundingDaoImpl implements FundingDao {

    @Override
    public void create(Funding funding) throws DaoException {
        String query;
        if (funding.getTEAM_ID() != 0) {
            query = "INSERT INTO fundings (item_id, team_id) "
                + "VALUES (?, ?)";
        } else {
            query = "INSERT INTO fundings (item_id) "
                + "VALUES (?)";
        }

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, funding.getITEM_ID());
            if (funding.getTEAM_ID() != 0) {
                preparedStatement.setInt(2, funding.getTEAM_ID());
            }
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("Failed to create a funding\n" + e);
        }
    }

    @Override
    public void update(Funding funding) throws DaoException {
        String query = "UPDATE fundings "
                + "SET item_id = ?, "
                + "team_id = ? "
                + "WHERE id = ?";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, funding.getITEM_ID());
            preparedStatement.setInt(4, funding.getTEAM_ID());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("Failed to update funding\n" + e);
        }
    }

    @Override
    public Funding extractFromResultSet(ResultSet resultSet) throws DaoException {
        int ID;
        int ITEM_ID;
        int TEAM_ID;
        Funding funding;

        try {
            ID = resultSet.getInt("id");
            ITEM_ID = resultSet.getInt("price");
            TEAM_ID = resultSet.getInt("type");
        } catch (SQLException e) {
            throw new DaoException("Failed to get funding from result set\n" + e);
        }

         funding = new Funding.Builder()
                .withID(ID)
                .withITEM_ID(ITEM_ID)
                 .withTEAM_ID(TEAM_ID)
                .build();

        return funding;
    }

    public int getFundingSequenceNextVal() throws DaoException{
        String query =
                "SELECT nextval('fundings_id_seq')";

        try(Connection connection = DatabaseConnector.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery()){
            rs.next();
            return rs.getInt("nextval");
        }catch (SQLException e){
            throw new DaoException("Failed to get next Fundings sequence number", e);
        }
    }
}
