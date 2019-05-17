package com.codecool.quest_store.service;

import com.codecool.quest_store.dao.DaoException;
import com.codecool.quest_store.dao.SessionDaoImpl;
import com.codecool.quest_store.dao.UserDaoImpl;
import com.codecool.quest_store.model.User;

import java.security.SecureRandom;

public class LoginService {

    UserDaoImpl userDao;
    SessionDaoImpl sessionDao;

    public LoginService() {
        userDao = new UserDaoImpl();
        sessionDao = new SessionDaoImpl();
    }

    public User getUser(String name, String password) {
        try {
            return userDao.getIdAndUserType(name, password);
        } catch (DaoException error) {
            error.printStackTrace();
        }
        return null;
    }

    public void createSession(int session, int userId) {
        try {
            sessionDao.createSession(session, userId);
        } catch (DaoException error) {
            error.printStackTrace();
        }
    }

    private void updateSession(int session, int userId) {
        try {
            sessionDao.updateSession(session, userId);
        } catch (DaoException error) {
            error.printStackTrace();
        }
    }

    public void deleteSession(int session) throws DaoException{
        sessionDao.deleteSession(session);
    }

    private Integer checkSession(int userId) {
        try {
            return sessionDao.getSession(userId);
        } catch (DaoException error) {
            error.printStackTrace();
        }
        return null;
    }

    public void getSession(int session, int userId) {
        createSession(session, userId);
    }

    public int generateNewSessionId() {
        SecureRandom secureRandom = new SecureRandom();
        return secureRandom.nextInt();
    }
}
