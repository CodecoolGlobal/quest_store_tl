package com.codecool.quest_store.service;

import com.codecool.quest_store.dao.DaoException;
import com.codecool.quest_store.dao.ItemDao;
import com.codecool.quest_store.dao.ItemDaoImpl;
import com.codecool.quest_store.model.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemService {

    private ItemDao itemDAO;

    public ItemService() {
        this.itemDAO = new ItemDaoImpl();
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

    public Item getItemById(int id) throws DaoException{
        return itemDAO.getItemById(id);
    }
}
