package com.codecool.quest_store.service;

import com.codecool.quest_store.dao.DaoException;
import com.codecool.quest_store.dao.SessionDaoImpl;
import com.codecool.quest_store.dao.UserDaoImpl;
import com.codecool.quest_store.model.User;

public class UserService {
    UserDaoImpl userDao;
    SessionDaoImpl sessionDao;

    public UserService() {
        userDao = new UserDaoImpl();
        sessionDao = new SessionDaoImpl();
    }

    public User getUser(int userId) {
        try {
            return userDao.getUser(userId);
        } catch (DaoException error) {
            error.printStackTrace();
        }
        return null;
    }

    public Integer getUserId(int session) {
        try {
            return sessionDao.getUserId(session);
        } catch (DaoException error) {
            error.printStackTrace();
        }
        return null;
    }
}
