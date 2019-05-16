package com.codecool.quest_store.service;

import com.codecool.quest_store.dao.*;
import com.codecool.quest_store.model.Funding;
import com.codecool.quest_store.model.Item;
import com.codecool.quest_store.model.Transaction;
import com.codecool.quest_store.model.User;
import org.apache.commons.lang3.ArrayUtils;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

public class ItemService {

    private ItemDao itemDAO;
    private TransactionDao transactionDao;
    private FundingDao fundingDao;

    private static final int[] ARTIFACT_TYPES = {1, 2};
    private static final int[] QUEST_TYPES = {3, 4};


    public ItemService() {
        this.itemDAO = new ItemDaoImpl();
        this.transactionDao = new TransactionDaoImpl();
        this.fundingDao = new FundingDaoImpl();
    }

    public List<Item> getAllItemsOfType(int itemType) {
        List<Item> itemsOfType = new ArrayList<>();
        List<Item> allItems = new ArrayList<>();
        try {
            allItems = itemDAO.getAllItems();
        } catch (DaoException e) {
            e.printStackTrace();
        }
        for(Item item : allItems){
            if (item.getType() == itemType) itemsOfType.add(item);
        }
        return itemsOfType;
    }

    public List<Item> getItemsByType(int itemType, List<Item> items) {
        List<Item> itemsOfType = new ArrayList<>();
        List<Item> allItems = items;

        for(Item item : allItems){
            if (item.getType() == itemType) itemsOfType.add(item);
        }
        return itemsOfType;
    }

    public Item getItemById(int id) throws DaoException{
        return itemDAO.getItemById(id);
    }

    public void registerNewTransaction(Funding funding, User user) throws DaoException{
        Transaction newTransaction;
        Item item = itemDAO.getItemById(funding.getITEM_ID());
        if (isArtifact(item)){
            newTransaction = new Transaction.Builder()
                    .withFUNDING_ID(funding.getID())
                    .withUSER_ID(user.getId())
                    .withTIMESTAMP(OffsetDateTime.now(ZoneOffset.UTC))
                    .withPAID_AMOUNT(item.getDiscountedPrice(itemDAO.getDiscount()))
                    .build();
        } else {
            newTransaction = new Transaction.Builder()
                    .withFUNDING_ID(funding.getID())
                    .withUSER_ID(user.getId())
                    .withTIMESTAMP(OffsetDateTime.now(ZoneOffset.UTC))
                    .withPAID_AMOUNT(item.getPrice())
                    .build();
        }
        ((Dao<Transaction>) transactionDao).create(newTransaction);
    }

    private boolean isArtifact(Item item) {
        return ArrayUtils.contains(ARTIFACT_TYPES, item.getType());
    }

    private boolean isQuest(Item item) {
        return ArrayUtils.contains(QUEST_TYPES, item.getType());
    }

    public Funding registerNewFunding(User user, Item item) throws DaoException {
        int newFundingId = fundingDao.getFundingSequenceNextVal();
//        System.out.println("funding next val = " + newFundingId);
        Funding newFunding;
        if (user.getTeamId() != 0) {
            newFunding = new Funding.Builder()
                    .withID(newFundingId)
                    .withITEM_ID(item.getID())
                    .withTEAM_ID(user.getTeamId())
                    .build();
        } else {
            newFunding = new Funding.Builder()
                    .withID(newFundingId)
                    .withITEM_ID(item.getID())
                    .build();
        }
        fundingDao.create(newFunding);
        return newFunding;
    }

    public void updateFundingStatusAsPending(Funding funding) throws DaoException {
        fundingDao.updateFundingStatus(funding, 2);
    }
}
