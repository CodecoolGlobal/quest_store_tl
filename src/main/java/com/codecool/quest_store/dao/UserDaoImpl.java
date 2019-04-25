package com.codecool.quest_store.dao;

import com.codecool.quest_store.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private User user;
    private List<User> users;
    private Connection connection;
    PreparedStatement pStatement;
    Statement statement;
    ResultSet resultSet;


    public UserDaoImpl() {
        users = new ArrayList<>();
    }

    /**
     *
     * @param userType Codecooler, Mentor or CreepyGuy
     */
    private List<User> getUsersByType(int userType){
        connection = DatabaseConnector.getConnection();
        String query = "SELECT * FROM usersg WHERE ";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while(resultSet.next()) {
                int id = resultSet.getInt("id" );
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String phoneNumber = resultSet.getString("phone_number");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String photo = resultSet.getString("id_user_type");
                String type = resultSet.getString("id_team");
                String room = resultSet.getString("id_room");
                user = new User.Builder()
                        .withId(id)
                        .withName(name)
                        .withSurname(surname)
                        .withPhoneNumber(phoneNumber)
                        .withEmail(email)
                        .withPassword(password)
                        .withPhoto(photo)
                        .withType(type)
                        .withRoom(room)
                        .build();
                users.add(user);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch(SQLException error){
            error.printStackTrace();
        }
        return users;
    }

    public List<User> getUsers(String userType) {
        getUsers(userType);
        return users;
    }

    public void createUser() {

    }

    public void updateUser() {

    }
}
