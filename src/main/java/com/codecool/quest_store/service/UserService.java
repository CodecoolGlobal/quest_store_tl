package com.codecool.quest_store.service;

import com.codecool.quest_store.dao.DaoException;
import com.codecool.quest_store.dao.ItemDaoImpl;
import com.codecool.quest_store.dao.SessionDaoImpl;
import com.codecool.quest_store.dao.UserDaoImpl;
import com.codecool.quest_store.model.Item;
import com.codecool.quest_store.model.User;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    UserDaoImpl userDao;
    SessionDaoImpl sessionDao;
    ItemDaoImpl itemDao;
    ItemService itemService;

    public  UserService() {
        userDao = new UserDaoImpl();
        sessionDao = new SessionDaoImpl();
        itemDao = new ItemDaoImpl();
        itemService = new ItemService();
    }

    private User getUser(int userId) throws DaoException {
            return userDao.getUser(userId);
    }

    private Integer getUserId(int session) throws DaoException {
        return sessionDao.getUserId(session);
    }

    public User getUserByCookie(String cookie) {
        try {
            int session = Integer.valueOf(ServiceUtility.parseData(cookie, ServiceUtility.SEMICOLON).get("session")
                    .replace("\"", ""));
            System.out.println(session);
            User student = getUser(getUserId(session));
            return student;
        }
        catch(UnsupportedEncodingException | DaoException e){
            e.printStackTrace();
        }
        return null;
    }

    private List<Item> getUserItems(int userId) {
        try {
            return itemDao.getUserItems(userId);
        } catch (DaoException error) {
            error.printStackTrace();
        }
        return null;
    }

    public List<Item> getUserArtifacts(int userId)  {
        return itemService.getItemsByType(ArtifactsService.NORMAL_ARTIFACT_TYPE, getUserItems(userId));
    }

    public List<Item> getUserBasicQuests(int userId)  {
        return itemService.getItemsByType(QuestService.BASIC_QUEST_TYPE, getUserItems(userId));
    }

    public List<Item> getUserExtraQuests(int userId) {
        return itemService.getItemsByType(QuestService.EXTRA_QUEST_TYPE, getUserItems(userId));
    }


}
