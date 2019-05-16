package com.codecool.quest_store.controllers;

import java.util.Map;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.nio.charset.StandardCharsets;

import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import com.codecool.quest_store.model.User;
import com.codecool.quest_store.model.UserDefaultPhoto;
import com.codecool.quest_store.model.UserType;

import com.codecool.quest_store.service.CreepyGuyService;
import com.codecool.quest_store.service.EmployeeService;
import com.codecool.quest_store.service.ServiceUtility;
import com.codecool.quest_store.service.UserService;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class CreepyGuyController implements HttpHandler {

    private EmployeeService employeeService;
    private CreepyGuyService creepyGuyService;
    private UserService userService;

    public CreepyGuyController() {
        employeeService = new EmployeeService();
        creepyGuyService = new CreepyGuyService();
        userService = new UserService();
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/creepy-guy.twig");
        JtwigModel model = JtwigModel.newModel();

        String method = httpExchange.getRequestMethod();
        String response;

        if (method.equals("GET")) {
            User student = userService.getUserByCookie(httpExchange.getRequestHeaders().get("Cookie").get(0));
            response = template.render(model);
            ServiceUtility.sendResponse(httpExchange, response);
        }

        if (method.equals("POST")) {
            InputStreamReader inputStreamReader = new InputStreamReader(httpExchange.getRequestBody(), StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String data = bufferedReader.readLine();
            Map<String, String> inputs = ServiceUtility.parseData(data, ServiceUtility.AND);

            handlePOST(inputs);

            response = template.render(model);
            ServiceUtility.redirectToContext(httpExchange, response, "/creepy-guy");
        }
    }

    private void handlePOST(Map<String, String> inputs) {

        String name;

        for (Map.Entry<String, String> entry : inputs.entrySet()) {

            if (entry.getKey().contains("recruit-mentor-button")) {
                name = inputs.get("mentor-name");
                String surname = inputs.get("mentor-surname");
                String email = inputs.get("mentor-email");
                employeeService.createUser(name, surname, email, UserType.MENTOR, UserDefaultPhoto.MENTOR);

            } else if (entry.getKey().contains("create-room-button")) {
                name = inputs.get("room-name");
                creepyGuyService.createRoom(name);
            }
        }
    }
}