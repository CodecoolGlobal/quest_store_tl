package com.codecool.quest_store.dao;

import java.util.List;
import java.util.Map;

import com.codecool.quest_store.model.User;

public interface UserDao {

    List<User> getUsersByType(int userType) throws DaoException;

    Map<String, Integer> getUserTypes() throws DaoException;
}
