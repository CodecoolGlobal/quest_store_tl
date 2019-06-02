package com.codecool.quest_store.controllers;

import java.util.List;

import java.io.IOException;

import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import com.codecool.quest_store.model.User;

import com.codecool.quest_store.service.EmployeeService;
import com.codecool.quest_store.service.ServiceUtility;
import com.codecool.quest_store.service.UserService;

public class MentorsController implements HttpHandler {

    private UserService userService;
    private EmployeeService employeeService;

    public MentorsController() {
        userService = new UserService();
        employeeService = new EmployeeService();
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        String method = httpExchange.getRequestMethod();

        if (method.equals("GET")) {
            User student = userService.getUserByCookie(httpExchange.getRequestHeaders().get("Cookie").get(0));
            renderMentor(httpExchange);
        }
    }

    private void createGETModel(JtwigModel model) {
        int mentorType = userService.getUserTypes().get("Mentor");
        List<User> mentors = employeeService.getUsers(mentorType);
        model.with("mentors", mentors);
    }

    private void renderMentor(HttpExchange httpExchange) throws IOException {
        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/mentors.twig");
        JtwigModel model = JtwigModel.newModel();
        createGETModel(model);
        String response = template.render(model);
        ServiceUtility.sendResponse(httpExchange, response);
    }
}