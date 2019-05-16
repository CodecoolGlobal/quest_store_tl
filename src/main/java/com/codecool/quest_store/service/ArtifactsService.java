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

public class ArtifactsService {

    private ItemDao itemDAO;
    private TransactionDao transactionDao;
    private ItemService itemService;
    private static final int NORMAL_ARTIFACT_TYPE = 1;
    private static final int MAGIC_ARTIFACT_TYPE = 2;


    public ArtifactsService() {
        this.itemDAO = new ItemDaoImpl();
        this.transactionDao = new TransactionDaoImpl();
        this.itemService = new ItemService();
    }

    public List<Item> getNormalArtifacts() {
        return itemService.getAllItemsOfType(NORMAL_ARTIFACT_TYPE);
    }


    public List<Item> getMagicArtifacts() {
        return itemService.getAllItemsOfType(MAGIC_ARTIFACT_TYPE);
    }

    public String respondToPostMethod(HttpExchange httpExchange, User user) throws IOException {
        String postData = new BufferedReader(new InputStreamReader(httpExchange.getRequestBody())).readLine();
        Map<String, String> postMap = ServiceUtility.parseData(postData, "&");

        if (postMap.keySet().contains("artifactId") && user.getTypeId() == 1) {
            int artifactId = Integer.parseInt(postMap.get("artifactId"));
            try {
                return handleArtifactPurchase(user, itemDAO.getItemById(artifactId));
            } catch (DaoException e) {
                e.printStackTrace();
                return e.getMessage();
            }
        } else if (postMap.keySet().contains("newDiscount") && user.getTypeId() == 2) {
            int newDiscount = Integer.parseInt(postMap.get("newDiscount"));
            try {
                return handleDiscountChange(newDiscount);
            } catch (DaoException e) {
                e.printStackTrace();
                return e.getMessage();
            }
        } else return "Invalid request";
    }

    private String handleDiscountChange(int newDiscount) throws DaoException {
        itemDAO.setDiscount(newDiscount);
        return "Discount changed";
    }

    private String handleArtifactPurchase(User user, Item artifact) throws DaoException {
        if (artifact.getType() == 1) {
            return handleIndividualPurchase(user, artifact);
        } else {
            return handleTeamPurchase(); //descoped from this sprint
        }
    }


    private String handleIndividualPurchase(User user, Item artifact) throws DaoException {
        if (canAffordPurchase(user, artifact)) {
            Funding newFunding = itemService.registerNewFunding(user, artifact);
            itemService.registerNewTransaction(newFunding, user);
            itemService.updateFundingStatusAsPending(newFunding);
            return "Artifact purchased";
//            need to update funding status? or can it auto-update
        }
        return insufficientFundsResponse();
    }


    private String insufficientFundsResponse() {
        System.out.println("Insufficient funds");
        return "Insufficient funds";
    }


    private boolean canAffordPurchase(User user, Item artifact) throws DaoException {
        int balance =
                transactionDao.getPriceSumOfRealizedQuests(user)
                        - transactionDao.getPriceSumOfPurchasedArtifacts(user);
//        System.out.println("Balance = " + balance + ", price = " + artifact.getPrice());
        return balance > artifact.getDiscountedPrice(itemDAO.getDiscount());
    }

    private String handleTeamPurchase() {
        return "";
    }

    public int getDiscount() {
        int discount;
        try {
            discount = itemDAO.getDiscount();
        } catch (DaoException e) {
            discount = 0;
            e.printStackTrace();
        }
        return discount;
    }
}
