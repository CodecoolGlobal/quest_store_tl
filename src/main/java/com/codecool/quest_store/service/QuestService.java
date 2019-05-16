package com.codecool.quest_store.service;

import com.codecool.quest_store.dao.*;
import com.codecool.quest_store.model.Funding;
import com.codecool.quest_store.model.Item;
import com.codecool.quest_store.model.User;
import com.sun.net.httpserver.HttpExchange;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

public class QuestService {


    private ItemDao itemDAO;
    private TransactionDao transactionDao;
    private FundingDao fundingDao;
    private ServiceUtility serviceUtility;
    private ItemService itemService;
    public static final int BASIC_QUEST_TYPE = 3;
    public static final int EXTRA_QUEST_TYPE = 4;

    public QuestService(){
        this.itemDAO = new ItemDaoImpl();
        this.transactionDao = new TransactionDaoImpl();
        this.fundingDao = new FundingDaoImpl();
        this.serviceUtility = new ServiceUtility();
        this.itemService = new ItemService();
    }

    public String respondToPostMethod(HttpExchange httpExchange, User user) throws IOException {
        String postData = new BufferedReader(new InputStreamReader(httpExchange.getRequestBody())).readLine();
        Map<String, String> postMap = serviceUtility.parseData(postData, "&");
        int questId = Integer.parseInt(postMap.get("questId"));
        try {
            return handleQuestClaim(user, itemDAO.getItemById(questId));
        } catch (DaoException e) {
            e.printStackTrace();
        } return postMap.toString();

    }

    private String handleQuestClaim(User user, Item quest) throws DaoException {
        Funding newFunding = itemService.registerNewFunding(user, quest);
        itemService.registerNewTransaction(newFunding, user);
        itemService.updateFundingStatusAsPending(newFunding);
        return "";
    }

    public List<Item> getBasicQuests() {
        return itemService.getAllItemsOfType(BASIC_QUEST_TYPE);
    }

    public List<Item> getExtraQuests() {
        return itemService.getAllItemsOfType(EXTRA_QUEST_TYPE);
    }

}
