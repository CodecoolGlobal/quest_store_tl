package com.codecool.quest_store.dao;

import com.codecool.quest_store.model.Codecooler;
import com.codecool.quest_store.model.Item;

public interface TransactionDao {

    public void createTransaction(Codecooler codecooler, Item item, int status, int coinsAmount);
    public void updateTransactionStatus(String column, int status, int idFunding);
    public int getPriceSumOfRealizedQuests(int userId);
    public int getPriceSumOfPurchasedArtifacts(int userId);
}