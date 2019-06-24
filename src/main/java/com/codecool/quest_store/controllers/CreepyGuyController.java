package com.codecool.quest_store.controllers;

import java.util.Map;

import java.io.IOException;

import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import com.codecool.quest_store.model.User;
import com.codecool.quest_store.model.UserDefaultPhoto;

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

        String method = httpExchange.getRequestMethod();

        if (method.equals("GET")) {
            userService.getUserByCookie(httpExchange.getRequestHeaders().get("Cookie").get(0));
            renderCreepyGuy(httpExchange);
        }

        if (method.equals("POST")) {
            handlePOST(httpExchange);
        }
    }

    private void handlePOST(HttpExchange httpExchange) throws IOException {
        Map<String, String> inputs = ServiceUtility.getPOSTInputs(httpExchange);

        for (Map.Entry<String, String> entry : inputs.entrySet()) {

            if (entry.getKey().contains("recruit-mentor-button")) {
                handleCreateMentor(inputs);

            } else if (entry.getKey().contains("create-room-button")) {
                handleCreateRoom(inputs);
            }
        }
        ServiceUtility.redirectToContext(httpExchange, "", "/creepy-guy");
    }

    private void renderCreepyGuy(HttpExchange httpExchange) throws IOException {
        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/creepy-guy.twig");
        JtwigModel model = JtwigModel.newModel();
        String response = template.render(model);
        ServiceUtility.sendResponse(httpExchange, response);
    }

    private void handleCreateMentor(Map<String, String> inputs) {
        String name = inputs.get("mentor-name");
        String surname = inputs.get("mentor-surname");
        String email = inputs.get("mentor-email");
        int userType = userService.getUserTypes().get("Mentor");
        employeeService.createUser(name, surname, email, userType, UserDefaultPhoto.MENTOR);
    }

    private void handleCreateRoom(Map<String, String> inputs) {
        String name = inputs.get("room-name");
        creepyGuyService.createRoom(name);
    }
}