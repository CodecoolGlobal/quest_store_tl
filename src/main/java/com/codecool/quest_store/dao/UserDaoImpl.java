package com.codecool.quest_store.dao;

import com.codecool.quest_store.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao, Dao<User> {

    @Override
    public List<User> getUsersByType(int userType) throws DaoException {
        User user;
        List<User> users = new ArrayList<>();

        String query = "SELECT * FROM users WHERE user_type_id = " + userType;

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String surname = resultSet.getString("surname");
                    String phoneNumber = resultSet.getString("phone_number");
                    String email = resultSet.getString("email");
                    String password = resultSet.getString("password");
                    String photo = resultSet.getString("photo");
                    int typeId = resultSet.getInt("user_type_id");
                    int roomId = resultSet.getInt("room_id");
                    int teamId = resultSet.getInt("team_id");
                    user = new User.UserBuilder()
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
            throw new DaoException("Failed get users by user type", error);
        }
        return users;
    }

    @Override
    public void create(User user) throws DaoException {
        String query = "INSERT INTO users(name, surname, phone_number, email, password, " +
                "photo, user_type_id, room_id) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?);";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            setRequiredStatements(statement, user);
            statement.executeUpdate();

        } catch(SQLException error){
            throw new DaoException("Your have some mistake during creation a new user", error);
        }
    }

    private void setRequiredStatements(PreparedStatement statement, User user) throws DaoException {
        try {
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getPhoneNumber());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getPassword());
            statement.setString(6, user.getPhoto());
            statement.setInt(7, user.getTypeId());
            statement.setInt(8, user.getRoomId());
        } catch (SQLException e) {
            throw new DaoException("Failed to set user's statements");
        }
    }

    public void update(User user) throws DaoException {
        String query = "UPDATE users SET name = ?, surname = ?, phone_number = ?, email = ?, password = ?, " +
                "photo = ?, user_type_id = ?, room_id = ?, team_id = ? WHERE id = ?";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            setRequiredStatements(statement, user);
            statement.setInt(9, user.getTeamId());
            statement.setInt(10, user.getId());
            statement.executeUpdate();

        } catch(SQLException error){
            throw new DaoException("You can't update a user", error);
        }
    }

    public User getUser(int userId) throws DaoException {
        String query = "SELECT * FROM users WHERE id = ?";
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();
            return getListByResultSet(rs).get(0);
        } catch(SQLException error){
            throw new DaoException("You can't update a user", error);
        }
    }

    @Override
    public User extractFromResultSet(ResultSet resultSet) throws DaoException {
        int id;
        String name;
        String surname;
        String phoneNumber;
        String email;
        String password;
        String photo;
        int userTypeId;
        int roomId;
        int teamId;
        User user;

        try {
            id = resultSet.getInt("id");
            name = resultSet.getString("name");
            surname = resultSet.getString("surname");
            phoneNumber = resultSet.getString("phone_number");
            email = resultSet.getString("email");
            password = resultSet.getString("password");
            photo = resultSet.getString("photo");
            userTypeId = resultSet.getInt("user_type_id");
            roomId = resultSet.getInt("room_id");
            teamId = resultSet.getInt("team_id");

        } catch (SQLException e) {
            throw new DaoException("Failed to get item from result set\n" + e);
        }

        user = new User.UserBuilder()
                .withId(id)
                .withName(name)
                .withSurname(surname)
                .withPhoneNumber(phoneNumber)
                .withEmail(email)
                .withPassword(password)
                .withPhoto(photo)
                .withTypeId(userTypeId)
                .withRoomId(roomId)
                .withTeamId(teamId)
                .build();

        return user;
    }


    public User getIdAndUserType(String name, String password) throws DaoException {

        User user;

        String query =  "SELECT * FROM users WHERE name LIKE ? AND password LIKE ?;";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.setString(2, password);
            try(ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    int userType = rs.getInt("user_type_id");
                    user = new User.UserBuilder()
                            .withId(id)
                            .withTypeId(userType)
                            .build();
                    return user;
                }
            }
        } catch(SQLException error) {
            throw new DaoException("There isn't an applicant with data: " + name + " " + password);
        }
        return null;
    }
}


