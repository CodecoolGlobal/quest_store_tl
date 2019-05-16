package com.codecool.quest_store.service;

import com.codecool.quest_store.dao.DaoException;
import com.codecool.quest_store.dao.SessionDaoImpl;
import com.codecool.quest_store.dao.UserDaoImpl;
import com.codecool.quest_store.model.User;

import java.io.UnsupportedEncodingException;

public class UserService {
    UserDaoImpl userDao;
    SessionDaoImpl sessionDao;

    public  UserService() {
        userDao = new UserDaoImpl();
        sessionDao = new SessionDaoImpl();
    }

    private User getUser(int userId) {
        try {
            return userDao.getUser(userId);
        } catch (DaoException error) {
            error.printStackTrace();
        }
        return null;
    }

    private Integer getUserId(int session) {
        try {
            return sessionDao.getUserId(session);
        } catch (DaoException error) {
            error.printStackTrace();
        }
        return null;
    }

    public User getUserByCookie(String cookie) {
        try {
            int session = Integer.valueOf(ServiceUtility.parseData(cookie, ServiceUtility.SEMICOLON).get("session")
                    .replace("\"", ""));
//            System.out.println(session);
            User student = getUser(getUserId(session));
            return student;
        }
        catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return null;
    }

}
