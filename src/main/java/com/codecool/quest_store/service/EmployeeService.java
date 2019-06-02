package com.codecool.quest_store.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.codecool.quest_store.dao.Dao;
import com.codecool.quest_store.dao.DaoException;
import com.codecool.quest_store.dao.RoomDao;
import com.codecool.quest_store.dao.RoomDaoImpl;
import com.codecool.quest_store.dao.UserDao;
import com.codecool.quest_store.dao.UserDaoImpl;

import com.codecool.quest_store.model.User;

import com.codecool.quest_store.model.UserDefaultPhoto;
import com.codecool.quest_store.view.View;

public class EmployeeService {

    private Dao<User> dao;
    private UserDao userDao;
    private RoomDao roomDao;
    private View view;

    public EmployeeService() {
        dao = new UserDaoImpl();
        userDao = new UserDaoImpl();
        roomDao = new RoomDaoImpl();
        view = new View();
    }

    public void createUser(String name, String surname, String email, int userType, UserDefaultPhoto USER_PHOTO) {
        User user = new User.UserBuilder()
                .withName(name)
                .withSurname(surname)
                .withPhoneNumber(null)
                .withEmail(email)
                .withPassword("123")
                .withPhoto(USER_PHOTO.getUserPhoto())
                .withTypeId(userType)
                .withRoomId(getRoomTypes().get("ProgBasic"))
                .build();

        try {
            dao.create(user);
            view.printSuccess("User has been created.");
        } catch (DaoException e) {
            view.printError(e.getMessage());
        }
    }

    public List<User> getUsers(int userType) {
        List<User> users = new ArrayList<>();

        try {
            users = userDao.getUsersByType(userType);
            view.printSuccess("Successfully retrieved users.");
        } catch (DaoException e) {
            view.printError(e.getMessage());
        }
        return users;
    }

    private Map<String, Integer> getRoomTypes() {
        Map<String, Integer> roomTypes = null;
        try {
            roomTypes = roomDao.getRoomTypes();
        } catch (DaoException e) {
            view.printError("Failed to get ProgBasic's type of room");
        }
        return roomTypes;
    }
}