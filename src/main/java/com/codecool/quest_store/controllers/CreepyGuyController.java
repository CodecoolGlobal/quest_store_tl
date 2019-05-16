package com.codecool.quest_store.controllers;

import java.util.Map;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

import java.nio.charset.StandardCharsets;

import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import com.codecool.quest_store.model.User;

import com.codecool.quest_store.service.CreepyGuyService;
import com.codecool.quest_store.service.LoginService;
import com.codecool.quest_store.service.ServiceUtility;
import com.codecool.quest_store.service.UserService;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class CreepyGuyController implements HttpHandler {

    private CreepyGuyService creepyGuyService;
    private LoginService login;
    private UserService userService;

    public CreepyGuyController() {
        creepyGuyService = new CreepyGuyService();
        login = new LoginService();
        userService = new UserService();
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/creepy-guy.twig");
        JtwigModel model = JtwigModel.newModel();

        String method = httpExchange.getRequestMethod();
        String response = "";

        if (method.equals("GET")) {
            String cookie = httpExchange.getRequestHeaders().get("Cookie").get(0);
            int session = Integer.valueOf(ServiceUtility.parseData(cookie, ServiceUtility.SEMICOLON).get("session")
                    .replace("\"", ""));
            User student = userService.getUser(userService.getUserId(session));

            response = template.render(model);
            httpExchange.sendResponseHeaders(200, response.getBytes().length);
        }

        if (method.equals("POST")) {
            InputStreamReader inputStreamReader = new InputStreamReader(httpExchange.getRequestBody(), StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String data = bufferedReader.readLine();
            Map<String, String> inputs = ServiceUtility.parseData(data, ServiceUtility.AND);

            handlePOST(inputs);

            response = template.render(model);
            httpExchange.getResponseHeaders().set("Location", "/creepy-guy");
            httpExchange.sendResponseHeaders(303, response.getBytes().length);
        }

        OutputStream outputStream = httpExchange.getResponseBody();
        outputStream.write(response.getBytes());
        outputStream.close();
    }

    private void handlePOST(Map<String, String> inputs) {

        String name;

        for (Map.Entry<String, String> entry : inputs.entrySet()) {

            if (entry.getKey().contains("recruit-mentor-button")) {
                name = inputs.get("mentor-name");
                String surname = inputs.get("mentor-surname");
                String email = inputs.get("mentor-email");
                creepyGuyService.createMentor(name, surname, email);

            } else if (entry.getKey().contains("create-room-button")) {
                name = inputs.get("room-name");
                creepyGuyService.createRoom(name);
            }
        }
    }
}