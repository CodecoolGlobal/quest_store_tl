package com.codecool.quest_store.service;

import com.codecool.quest_store.dao.Dao;
import com.codecool.quest_store.dao.DaoException;
import com.codecool.quest_store.dao.RoomDaoImpl;

import com.codecool.quest_store.model.Room;

import com.codecool.quest_store.view.View;

public class CreepyGuyService {

    private Dao<Room> roomDao;
    private View view;

    public CreepyGuyService() {
        roomDao = new RoomDaoImpl();
        view = new View();
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