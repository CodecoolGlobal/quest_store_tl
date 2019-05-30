package com.codecool.quest_store.controllers;

import java.util.List;

import java.io.IOException;

import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import com.codecool.quest_store.model.User;
import com.codecool.quest_store.model.UserType;

import com.codecool.quest_store.service.EmployeeService;
import com.codecool.quest_store.service.ServiceUtility;
import com.codecool.quest_store.service.UserService;

public class StudentsController implements HttpHandler {

    private UserService userService;
    private EmployeeService employeeService;

    public StudentsController() {
        userService = new UserService();
        employeeService = new EmployeeService();
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        String method = httpExchange.getRequestMethod();

        if (method.equals("GET")) {
            User activeUser = userService.getUserByCookie(httpExchange.getRequestHeaders().get("Cookie").get(0));
            renderCodecoolers(httpExchange);
        }
    }

    private void createGETModel(JtwigModel model) {
        List<User> codecoolers = employeeService.getUsers(UserType.CODECOOLER);
        model.with("codecoolers", codecoolers);
        model.with("balance", userService);
    }

    private void renderCodecoolers(HttpExchange httpExchange) throws IOException {
        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/students.twig");
        JtwigModel model = JtwigModel.newModel();
        createGETModel(model);
        String response = template.render(model);
        ServiceUtility.sendResponse(httpExchange, response);
    }
}