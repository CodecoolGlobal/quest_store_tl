package com.codecool.quest_store.dao;

import java.util.Map;

public interface RoomDao {

    public Map<String, Integer> getRoomTypes() throws DaoException;

}