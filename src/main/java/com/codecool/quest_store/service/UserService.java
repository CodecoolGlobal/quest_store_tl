package com.codecool.quest_store.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import com.codecool.quest_store.dao.*;
import com.codecool.quest_store.model.Item;
import com.codecool.quest_store.model.User;
import com.codecool.quest_store.view.View;

public class UserService {
    private UserDaoImpl userDao;
    private SessionDaoImpl sessionDao;
    private ItemDaoImpl itemDao;
    private ItemService itemService;
    private TransactionDaoImpl transactionDao;
    private LoginService loginService;
    private View view;

    public  UserService() {
        userDao = new UserDaoImpl();
        sessionDao = new SessionDaoImpl();
        itemDao = new ItemDaoImpl();
        itemService = new ItemService();
        transactionDao = new TransactionDaoImpl();
        loginService = new LoginService();
        view = new View();
    }

    private User getUser(int userId) throws DaoException {
            return userDao.getUser(userId);
    }

    private Integer getUserId(int session) throws DaoException {
        return sessionDao.getUserId(session);
    }

    private Integer getSessionFromCookie(String cookie) throws UnsupportedEncodingException {
        if(!(Integer.valueOf(ServiceUtility.parseData(cookie, ServiceUtility.SEMICOLON).get("session")
                .replace("\"", "")) == null)) {
            return Integer.valueOf(ServiceUtility.parseData(cookie, ServiceUtility.SEMICOLON).get("session")
                    .replace("\"", ""));
        }
        return null;
    }

    public User getUserByCookie(String cookie) {
        try {
            int session = getSessionFromCookie(cookie);
            return getUser(getUserId(session));
        }
        catch(UnsupportedEncodingException | DaoException error){
            view.printError(error.getMessage());
        }
        return null;
    }

    private List<Item> getUserItems(int userId) {
        try {
            return itemDao.getUserItems(userId);
        } catch (DaoException error) {
            view.printError(error.getMessage());
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

    public Integer getBalance(User user) {
        try {
            return transactionDao.getPriceSumOfRealizedQuests(user)
                    - transactionDao.getPriceSumOfPurchasedArtifacts(user);
        } catch (DaoException error) {
            view.printError(error.getMessage());
        }
        return null;
    }

    public void deleteSession(String cookie) {
        try{
            loginService.deleteSession(getSessionFromCookie(cookie));
        } catch (UnsupportedEncodingException | DaoException error) {
            view.printError(error.getMessage());
        }
    }

    public Map<String, Integer> getUserTypes() {
        Map<String, Integer> userTypes = null;
        try {
           userTypes = userDao.getUserTypes();
        } catch (DaoException e) {
            view.printError(e.getMessage());
        }
        return  userTypes;
    }
}
