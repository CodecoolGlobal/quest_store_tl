package com.codecool.quest_store.dao;

import com.codecool.quest_store.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private User user;

    /**
     *
     * @param userType
     * This function only for programmers, user won't use it
     * for this reason this function without Prepared Statement
     */
    @Override
    public List<User> getUsersByType(int userType) throws DaoException {
        List<User> users = new ArrayList<>();

        String query = "SELECT * FROM users WHERE id_user_type = " + userType;

        try (Connection connection = DatabaseConnector.getConnection();
             Statement st = connection.createStatement();
             ResultSet resultSet = st.executeQuery(query)) {

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String surname = resultSet.getString("surname");
                    String phoneNumber = resultSet.getString("phone_number");
                    String email = resultSet.getString("email");
                    String password = resultSet.getString("password");
                    String photo = resultSet.getString("id_user_type");
                    int typeId = resultSet.getInt("id_team");
                    int roomId = resultSet.getInt("id_room");
                    int teamId = resultSet.getInt("id_team");
                    user = new User.Builder()
                            .withId(id)
                            .withName(name)
                            .withSurname(surname)
                            .withPhoneNumber(phoneNumber)
                            .withEmail(email)
                            .withPassword(password)
                            .withPhoto(photo)
                            .withTypeId(typeId)
                            .withRoomId(roomId)
                            .withTeamId(teamId)
                            .build();
                    users.add(user);
                }

        } catch(SQLException error){
            //error.printStackTrace();
            throw new DaoException("Failed get users by user type", error);
        }
        return users;
    }

    //If I need object of user in parameter instead of parameters
    @Override
    public void createUser(User user) throws DaoException {
        String query = "INSERT INTO users(name, surname, phone_number, email, password, " +
                "photo, id_user_type, id_room, id_team) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getPhoneNumber());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getPassword());
            statement.setString(6, user.getPhoto());
            statement.setInt(7, user.getTypeId());
            statement.setInt(8, user.getRoomId());
            statement.setInt(9, user.getTeamId());
            statement.executeUpdate();

        } catch(SQLException error){
            throw new DaoException("Your have some mistake during creation a new user", error);
        }
    }

    @Override
    public void updateUserEmail(User user, String email) throws DaoException {
        String query = "UPDATE users SET email = ? WHERE id = ?";
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            statement.setInt(2, user.getId());
            statement.executeUpdate();
        } catch(SQLException error){
            throw new DaoException("You can't update a user", error);
        }
    }

    @Override
    public void updateUserRoom(User user, int room) throws DaoException {
        String query = "UPDATE users SET id_room = ? WHERE id = ?";
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, room);
            statement.setInt(2, user.getId());
            statement.executeUpdate();

        } catch(SQLException error){
            throw new DaoException("You can't update a user", error);
        }
    }

}

