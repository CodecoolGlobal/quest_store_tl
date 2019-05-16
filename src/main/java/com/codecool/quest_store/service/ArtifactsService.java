package com.codecool.quest_store.service;

import com.codecool.quest_store.dao.*;
import com.codecool.quest_store.model.Funding;
import com.codecool.quest_store.model.Item;
import com.codecool.quest_store.model.Transaction;
import com.codecool.quest_store.model.User;
import com.sun.net.httpserver.HttpExchange;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ArtifactsService {

    private ItemDao itemDAO;
    private TransactionDao transactionDao;
    private FundingDao fundingDao;
    private ServiceUtility serviceUtility;
    private static final int NORMAL_ARTIFACT_TYPE = 1;
    private static final int MAGIC_ARTIFACT_TYPE = 2;


    public ArtifactsService() {
        this.itemDAO = new ItemDaoImpl();
        this.transactionDao = new TransactionDaoImpl();
        this.fundingDao = new FundingDaoImpl();
        this.serviceUtility = new ServiceUtility();
    }

    public List<Item> getNormalArtifacts(){
      return getAllArtifactsOfType(NORMAL_ARTIFACT_TYPE);
    }


    public List<Item> getMagicArtifacts(){
        return getAllArtifactsOfType(MAGIC_ARTIFACT_TYPE);
    }

    private List<Item> getAllArtifactsOfType(int artifactType) {
        List<Item> artifactsOfType = new ArrayList<>();
        List<Item> allArtifacts = new ArrayList<>();
        try {
            allArtifacts = itemDAO.getAllArtifacts();
        } catch (DaoException e) {
            e.printStackTrace();
        }
        for(Item artifact : allArtifacts){
            if (artifact.getType() == artifactType) artifactsOfType.add(artifact);
        }
        return artifactsOfType;
    }

    public Item getItemById(int id) throws DaoException{
            return itemDAO.getItemById(id);
    }

    public String respondToPostMethod(HttpExchange httpExchange, User user) throws IOException{
        String postData = new BufferedReader(new InputStreamReader(httpExchange.getRequestBody())).readLine();
        Map<String, String> postMap = serviceUtility.parseData(postData, "&");
        int artifactId = Integer.parseInt(postMap.get("artifactId"));
        try {
            return handleArtifactPurchase(user, getItemById(artifactId));
        } catch (DaoException e) {
            e.printStackTrace();
        } return postMap.toString();
    }

    private String handleArtifactPurchase(User user, Item artifact) throws DaoException{
        if (artifact.getType() == 1 ){
            return handleIndividualPurchase(user, artifact);
        } else {
            return handleTeamPurchase(); //descoped from this sprint
        }
    }


    private String handleIndividualPurchase(User user, Item artifact) throws DaoException {
        if (canAffordPurchase(user, artifact)) {
            Funding newFunding = registerNewFunding(user, artifact);
            registerNewTransaction(newFunding, user);
            updateFundingStatusAsComplete(newFunding);
            return "Artifact purchased";
//            need to update funding status? or can it auto-update
        }
        return insufficientFundsResponse();
    }

    private void updateFundingStatusAsComplete(Funding funding) throws DaoException {
        ((FundingDao) fundingDao).updateFundingStatus(funding, 2);
    }

    private String insufficientFundsResponse() {
        System.out.println("Insufficient funds");
        return "Insufficient funds";
    }

    private void registerNewTransaction(Funding funding, User user) throws DaoException{
        Transaction newTransaction = new Transaction.Builder()
                .withFUNDING_ID(funding.getID())
                .withUSER_ID(user.getId())
                .withTIMESTAMP(OffsetDateTime.now(ZoneOffset.UTC))
                .withPAID_AMOUNT(itemDAO.getItemById(funding.getITEM_ID()).getPrice())
                .build();
        ((Dao<Transaction>) transactionDao).create(newTransaction);
    }

    private Funding registerNewFunding(User user, Item artifact) throws DaoException {
        int newFundingId = fundingDao.getFundingSequenceNextVal();
//        System.out.println("funding next val = " + newFundingId);
        Funding newFunding;
        if (user.getTeamId() != 0) {
            newFunding = new Funding.Builder()
                .withID(newFundingId)
                .withITEM_ID(artifact.getID())
                .withTEAM_ID(user.getTeamId())
                .build();
        } else {
        newFunding = new Funding.Builder()
                .withID(newFundingId)
                .withITEM_ID(artifact.getID())
                .build();
        }
        fundingDao.create(newFunding);
        return newFunding;
    }

    private boolean canAffordPurchase(User user, Item artifact) throws DaoException {
        int balance =
                transactionDao.getPriceSumOfRealizedQuests(user)
                - transactionDao.getPriceSumOfPurchasedArtifacts(user);
//        System.out.println("Balance = " + balance + ", price = " + artifact.getPrice());
        return balance > artifact.getPrice();
    }

    private String handleTeamPurchase() {
        return "";
    }
}
