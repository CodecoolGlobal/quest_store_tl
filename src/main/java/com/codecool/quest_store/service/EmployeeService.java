package com.codecool.quest_store.service;

import java.util.ArrayList;
import java.util.List;

import com.codecool.quest_store.dao.Dao;
import com.codecool.quest_store.dao.DaoException;
import com.codecool.quest_store.dao.UserDao;
import com.codecool.quest_store.dao.UserDaoImpl;

import com.codecool.quest_store.model.RoomType;
import com.codecool.quest_store.model.User;

import com.codecool.quest_store.model.UserDefaultPhoto;
import com.codecool.quest_store.model.UserType;
import com.codecool.quest_store.view.View;

public class EmployeeService {

    private Dao<User> dao;
    private UserDao userDao;
    private View view;

    public EmployeeService() {
        dao = new UserDaoImpl();
        userDao = new UserDaoImpl();
        view = new View();
    }

    public void createUser(String name, String surname, String email, UserType USER_TYPE, UserDefaultPhoto USER_PHOTO) {
        User user;

        user = new User.UserBuilder()
                .withName(name)
                .withSurname(surname)
                .withPhoneNumber(null)
                .withEmail(email)
                .withPassword("123")
                .withPhoto(USER_PHOTO.getUserPhoto())
                .withTypeId(USER_TYPE.getUserType())
                .withRoomId(RoomType.PROGBASIC.getRoomType())
                .build();

        try {
            dao.create(user);
            view.printSuccess("User has been created.");
        } catch (DaoException e) {
            view.printError(e.getMessage());
        }
    }

    public List<User> getUsers(UserType USER_TYPE) {
        List<User> users = new ArrayList<>();

        try {
            users = userDao.getUsersByType(USER_TYPE.getUserType());
            view.printSuccess("Successfully retrieved users.");
        } catch (DaoException e) {
            view.printError(e.getMessage());
        }
        return users;
    }
}