package com.codecool.quest_store.dao;

import com.codecool.quest_store.model.Transaction;
import com.codecool.quest_store.model.User;

public interface TransactionDao {

    public int getPriceSumOfRealizedQuests(User user) throws DaoException;

    public int getPriceSumOfPurchasedArtifacts(User user) throws DaoException;
}