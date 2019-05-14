package com.codecool.quest_store.service;

import java.util.ArrayList;
import java.util.List;

import com.codecool.quest_store.dao.Dao;
import com.codecool.quest_store.dao.DaoException;
import com.codecool.quest_store.dao.RoomDaoImpl;
import com.codecool.quest_store.dao.UserDao;
import com.codecool.quest_store.dao.UserDaoImpl;

import com.codecool.quest_store.model.Mentor;
import com.codecool.quest_store.model.Room;
import com.codecool.quest_store.model.User;

import com.codecool.quest_store.view.View;

public class CreepyGuyService {

    private Dao<User> mentorDao;
    private UserDao userDao;
    private Dao<Room> roomDao;
    private View view;
    private final int USER_TYPE_ID = 2;

    public CreepyGuyService() {
        mentorDao = new UserDaoImpl();
        userDao = new UserDaoImpl();
        roomDao = new RoomDaoImpl();
        view = new View();
    }

    public void createMentor(String name, String surname, String email) {
        User mentor;
        String password = "mentor";
        String photo = "../static/assets/images/user-icons/mentor.png";

        mentor = new Mentor.UserBuilder()
                .withName(name)
                .withSurname(surname)
                .withEmail(email)
                .withPassword(password)
                .withPhoto(photo)
                .withTypeId(USER_TYPE_ID)
                .build();

        try {
            mentorDao.create(mentor);
            view.printSuccess("Mentor has been created.");
        } catch (DaoException e) {
            view.printError(e.getMessage());
        }
    }

    public List<User> getMentors() {
        List<User> mentors = new ArrayList<>();

        try {
            mentors = userDao.getUsersByType(USER_TYPE_ID);
            view.printSuccess("Successfully retrieved mentors.");
        } catch (DaoException e) {
            view.printError(e.getMessage());
        }
        return mentors;
    }

    public void createRoom(String name) {
        Room room = new Room.RoomBuilder()
                .withRoomName(name)
                .build();

        try {
            roomDao.create(room);
            view.printSuccess("Room has been created.");
        } catch (DaoException e) {
            view.printError(e.getMessage());
        }
    }
}