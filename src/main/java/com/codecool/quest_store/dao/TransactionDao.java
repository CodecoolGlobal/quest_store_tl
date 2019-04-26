package com.codecool.quest_store.dao;

import com.codecool.quest_store.model.Codecooler;
import com.codecool.quest_store.model.Item;

public interface TransactionDao {

    public void createTransaction(int idFunding, Codecooler codecooler, Item item, int status, int coinsAmount) throws DaoException;

    public void updateTransactionStatus(String column, int columnId, int status, int idFunding) throws DaoException;

    public int getPriceSumOfRealizedQuests(int userId) throws DaoException;

    public int getPriceSumOfPurchasedArtifacts(int userId) throws DaoException;
}