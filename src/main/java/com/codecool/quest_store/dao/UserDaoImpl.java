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
    //Statement statement;
    ResultSet resultSet;


    public UserDaoImpl() {
        users = new ArrayList<>();
    }

    private List<User> getUsersByType(){
        String query = "SELECT * FROM usersg WHERE ";
        try (Connection connection = DatabaseConnector.getConnection();
             Statement statement = connection.createStatement()) {
            try(ResultSet resultSet = statement.executeQuery(query)) {

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String surname = resultSet.getString("surname");
                    String phoneNumber = resultSet.getString("phone_number");
                    String email = resultSet.getString("email");
                    String password = resultSet.getString("password");
                    String photo = resultSet.getString("id_user_type");
                    int type = resultSet.getInt("id_team");
                    int room = resultSet.getInt("id_room");
                    int team = resultSet.getInt("id_team");
                    user = new User.Builder()
                            .withId(id)
                            .withName(name)
                            .withSurname(surname)
                            .withPhoneNumber(phoneNumber)
                            .withEmail(email)
                            .withPassword(password)
                            .withPhoto(photo)
                            .withTypeId(type)
                            .withRoomId(room)
                            .withTeamId(team)
                            .build();
                    users.add(user);
                    System.out.println("user added");
                }
            }
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

    public static void main(String[] args) {
        UserDaoImpl dao = new UserDaoImpl();
        dao.getUsers("Codecooler");
    }
}
