package com.codecool.quest_store.service;

import com.codecool.quest_store.dao.Dao;
import com.codecool.quest_store.dao.DaoException;
import com.codecool.quest_store.dao.ItemDaoImpl;

import com.codecool.quest_store.model.Item;

import com.codecool.quest_store.view.View;

public class MentorService {

    private Dao<Item> itemDao;
    private View view;

    public MentorService() {
        itemDao = new ItemDaoImpl();
        view = new View();
    }

    public void createItem(String title, String description, int price, int itemType) {
        Item item = new Item.Builder()
                .withTitle(title)
                .withDescription(description)
                .withPrice(price)
                .withType(itemType)
                .build();

        try {
            itemDao.create(item);
            view.printSuccess("Item has been created");
        } catch (DaoException e) {
            view.printError(e.getMessage());
        }
    }
}