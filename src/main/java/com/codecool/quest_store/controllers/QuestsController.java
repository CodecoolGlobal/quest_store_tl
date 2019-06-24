package com.codecool.quest_store.controllers;

import com.codecool.quest_store.dao.DaoException;
import com.codecool.quest_store.model.Item;
import com.codecool.quest_store.model.User;
import com.codecool.quest_store.service.QuestService;
import com.codecool.quest_store.service.ServiceUtility;
import com.codecool.quest_store.service.UserService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.IOException;
import java.util.List;

public class QuestsController implements HttpHandler {

    private static final String QUEST_CONTEXT_PATH = "/quests";
    private QuestService questService;
    private UserService userService;
    private User activeUser;

    public QuestsController(){
        this.questService = new QuestService();
        this.userService = new UserService();
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        User activeUser = userService.getUserByCookie(httpExchange.getRequestHeaders().get("Cookie").get(0));

        String method = httpExchange.getRequestMethod();
        if (method.equals("GET")){
            renderQuests(httpExchange, activeUser);
        } else if (method.equals("POST")){
            String response = questService.respondToPostMethod(httpExchange, activeUser);
            ServiceUtility.redirectToContext(httpExchange, response, QUEST_CONTEXT_PATH);
        }
    }

    private void renderQuests(HttpExchange httpExchange, User user) throws IOException{
        List<Item> basicQuests = questService.getBasicQuests();
        List<Item> extraQuests = questService.getExtraQuests();

        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/quests.twig");
        JtwigModel model = JtwigModel.newModel();

        model.with("basic_quests", basicQuests);
        model.with("extra_quests", extraQuests);
        model.with("user", user);

        String response = template.render(model);

        ServiceUtility.sendResponse(httpExchange, response);
    }
}

