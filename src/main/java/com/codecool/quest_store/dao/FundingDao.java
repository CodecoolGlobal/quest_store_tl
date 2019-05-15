package com.codecool.quest_store.dao;

import com.codecool.quest_store.model.Funding;

public interface FundingDao extends Dao<Funding> {
    public int getFundingSequenceNextVal() throws DaoException;
}
