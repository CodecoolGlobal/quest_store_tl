package com.codecool.quest_store.dao;

import com.codecool.quest_store.model.User;

import java.util.List;

public interface UserDao {
    List<User> getUsersByType(int userType) throws DaoException;
}
