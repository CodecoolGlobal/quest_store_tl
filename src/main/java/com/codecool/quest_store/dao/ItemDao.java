package com.codecool.quest_store.dao;

import com.codecool.quest_store.model.Item;

import java.util.List;

public interface ItemDao extends Dao<Item> {

    List<Item> getAllArtifacts() throws DaoException;
}
