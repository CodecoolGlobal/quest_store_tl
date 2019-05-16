package com.codecool.quest_store.controllers;

import java.util.List;

import java.io.IOException;

import com.codecool.quest_store.service.CreepyGuyService;
import com.codecool.quest_store.service.ServiceUtility;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import com.codecool.quest_store.model.User;
import com.codecool.quest_store.service.UserService;

public class MentorsController implements HttpHandler {

    private UserService userService;
    private CreepyGuyService creepyGuyService;

    public MentorsController() {
        userService = new UserService();
        creepyGuyService = new CreepyGuyService();
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/mentors.twig");
        JtwigModel model = JtwigModel.newModel();

        String method = httpExchange.getRequestMethod();
        String response;

        if (method.equals("GET")) {
            User student = userService.getUserByCookie(httpExchange.getRequestHeaders().get("Cookie").get(0));
            createGETModel(model);
            response = template.render(model);
            ServiceUtility.sendResponse(httpExchange, response);
        }
    }

    private void createGETModel(JtwigModel model) {
        List<User> mentors = creepyGuyService.getMentors();
        model.with("mentors", mentors);
    }
}