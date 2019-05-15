package com.codecool.quest_store.service;

import com.codecool.quest_store.dao.DaoException;
import com.codecool.quest_store.dao.UserDaoImpl;
import com.codecool.quest_store.model.User;

import java.security.SecureRandom;

public class LoginService {

    UserDaoImpl userDao = new UserDaoImpl();

    public User getUser(String name, String password) {
        try {
            return userDao.getIdAndUserType(name, password);
        } catch (DaoException error) {
            error.printStackTrace();
        }
        return null;
    }

    private void createSession(int session, int userId) {
        try {
            userDao.createSession(session, userId);
        } catch (DaoException error) {
            error.printStackTrace();
        }
    }

    private void updateSession(int session, int userId) {
        try {
            userDao.updateSession(session, userId);
        } catch (DaoException error) {
            error.printStackTrace();
        }
    }

    private Integer checkSession(int userId) {
        try {
            return userDao.getSession(userId);
        } catch (DaoException error) {
            error.printStackTrace();
        }
        return null;
    }

    public void getSession(int session, int userId) {
        if (!(checkSession(userId) == null)) {
            updateSession(session, userId);
        } else {
            createSession(session, userId);
        }
    }

    public int generateNewSessionId() {
        SecureRandom secureRandom = new SecureRandom();
        return secureRandom.nextInt();
    }
}
