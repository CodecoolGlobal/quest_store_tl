package com.codecool.quest_store.dao;

import java.util.List;
import java.util.Map;

import com.codecool.quest_store.model.Item;

public interface ItemDao extends Dao<Item> {

    List<Item> getAllItems() throws DaoException;

    Item getItemById(int itemId) throws DaoException;

    int getDiscount() throws DaoException;

    void setDiscount(int newDiscount) throws DaoException;

    Map<String, Integer> getItemTypes() throws DaoException;
}

