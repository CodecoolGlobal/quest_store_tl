package com.codecool.quest_store.service;

import com.codecool.quest_store.dao.*;
import com.codecool.quest_store.model.Item;
import com.codecool.quest_store.model.User;
import com.sun.net.httpserver.HttpExchange;

import java.util.ArrayList;
import java.util.List;

public class QuestService {


    private ItemDao itemDAO;
    private TransactionDao transactionDao;
    private FundingDao fundingDao;
    private ServiceUtility serviceUtility;
    private ItemService itemService;
    private static final int BASIC_QUEST_TYPE = 3;
    private static final int EXTRA_QUEST_TYPE = 4;

    public QuestService(){
        this.itemDAO = new ItemDaoImpl();
        this.transactionDao = new TransactionDaoImpl();
        this.fundingDao = new FundingDaoImpl();
        this.serviceUtility = new ServiceUtility();
        this.itemService = new ItemService();
    }

    public String respondToPostMethod(HttpExchange httpExchange, User activeUser) {
        return "";
    }

    public List<Item> getBasicQuests() {
        return itemService.getAllItemsOfType(BASIC_QUEST_TYPE);
    }

    public List<Item> getExtraQuests() {
        return itemService.getAllItemsOfType(EXTRA_QUEST_TYPE);
    }

}
