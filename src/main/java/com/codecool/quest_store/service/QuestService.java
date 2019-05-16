package com.codecool.quest_store.service;

import com.codecool.quest_store.model.Item;
import com.codecool.quest_store.model.User;
import com.sun.net.httpserver.HttpExchange;

import java.util.ArrayList;
import java.util.List;

public class QuestService {
    public String respondToPostMethod(HttpExchange httpExchange, User activeUser) {
        return "";
    }

    public List<Item> getBasicQuests() {
        return new ArrayList<>();
    }

    public List<Item> getExtraQuests() {
        return new ArrayList<>();
    }
}
