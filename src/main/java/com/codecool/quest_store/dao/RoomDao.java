package com.codecool.quest_store.dao;

import com.codecool.quest_store.model.Room;

public interface RoomDao {
    public void createRoom(Room room) throws DaoException;
}
