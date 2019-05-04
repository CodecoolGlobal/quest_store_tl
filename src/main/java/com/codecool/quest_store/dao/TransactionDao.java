package com.codecool.quest_store.dao;

import com.codecool.quest_store.model.Transaction;

public interface TransactionDao {

    public int getPriceSumOfRealizedQuests(Transaction transaction) throws DaoException;

    public int getPriceSumOfPurchasedArtifacts(Transaction transaction) throws DaoException;
}