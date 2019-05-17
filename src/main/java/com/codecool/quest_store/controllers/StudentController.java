package com.codecool.quest_store.controllers;

import com.codecool.quest_store.model.Item;
import com.codecool.quest_store.model.User;
import com.codecool.quest_store.service.ServiceUtility;
import com.codecool.quest_store.service.UserService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class StudentController implements HttpHandler {

    private UserService userService;


    public StudentController() {
        userService = new UserService();
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String method = httpExchange.getRequestMethod();
        System.out.println(method);
        User student = userService.getUserByCookie(httpExchange.getRequestHeaders().get("Cookie").get(0));
        String response = "";
        if(method.equals("GET")) {
            renderUser(httpExchange, student);
        }
        if(method.equals("POST")) {
            logout(httpExchange);
        }

    }

    private void renderUser(HttpExchange httpExchange, User student) throws IOException {

        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/student.twig");
        JtwigModel model = JtwigModel.newModel();

        model.with("name", student.getName());
        model.with("surname", student.getSurname());
        model.with("email", student.getEmail());
        model.with("phone", student.getPhoneNumber());
        model.with("wallet", userService.getBalance(student));

        List<Item> artifacts = userService.getUserArtifacts(student.getId());
        List<Item> basicQuests = userService.getUserBasicQuests(student.getId());
        List<Item> extraQuests = userService.getUserExtraQuests(student.getId());

        model.with("artifacts", artifacts);
        model.with("basic_quests", basicQuests);
        model.with("extra_quests", extraQuests);

        String response = template.render(model);
        ServiceUtility.sendResponse(httpExchange, response);
    }

    private void logout(HttpExchange httpExchange) throws IOException {
        String response = "";
        userService.deleteSession(httpExchange.getRequestHeaders().get("Cookie").get(0));
        httpExchange.getResponseHeaders().add("Location", "http://localhost:8000/login");

        httpExchange.sendResponseHeaders(301, response.getBytes().length);

        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}