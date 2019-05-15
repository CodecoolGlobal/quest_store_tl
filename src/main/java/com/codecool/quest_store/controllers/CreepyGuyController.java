package com.codecool.quest_store.controllers;

import java.util.HashMap;
import java.util.Map;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import java.nio.charset.StandardCharsets;

import java.net.URLDecoder;

import com.codecool.quest_store.service.CreepyGuyService;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class CreepyGuyController implements HttpHandler {

    private CreepyGuyService creepyGuyService;

    public CreepyGuyController() {
        creepyGuyService = new CreepyGuyService();
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/creepy-guy.twig");
        JtwigModel model = JtwigModel.newModel();

        String method = httpExchange.getRequestMethod();
        String response = "";

        if (method.equals("GET")) {
            response = template.render(model);
            httpExchange.sendResponseHeaders(200, response.getBytes().length);
        }

        if (method.equals("POST")) {
            InputStreamReader inputStreamReader = new InputStreamReader(httpExchange.getRequestBody(), StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String data = bufferedReader.readLine();
            Map<String, String> inputs = parse(data);

            handlePOST(inputs);

            response = template.render(model);
            httpExchange.getResponseHeaders().set("Location", "/creepy-guy");
            httpExchange.sendResponseHeaders(302, response.getBytes().length);
        }

        OutputStream outputStream = httpExchange.getResponseBody();
        outputStream.write(response.getBytes());
        outputStream.close();
    }

    private Map<String, String> parse(String data) throws UnsupportedEncodingException {

        Map<String, String> map = new HashMap<>();
        String[] pairs = data.split("&");
        String[] keyValue;
        String value;

        for (String pair : pairs) {
            keyValue = pair.split("=");
            value = URLDecoder.decode(keyValue[1], "UTF-8");
            map.put(keyValue[0], value);
        }
        return map;
    }

    private void handlePOST(Map<String, String> inputs) {

        for (Map.Entry<String, String> entry : inputs.entrySet()) {
            if (entry.getKey().contains("recruit-mentor-button")) {
                String name = inputs.get("mentor-name");
                String surname = inputs.get("mentor-surname");
                String email = inputs.get("mentor-email");
                creepyGuyService.createMentor(name, surname, email);
            }
        }
    }
}
