package com.codecool.quest_store.controllers;

import com.codecool.quest_store.model.Item;
import com.codecool.quest_store.model.User;
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
        String response = "";
        String method = httpExchange.getRequestMethod();
        System.out.println(method);
        List<Item> artifacts;
        List<Item> quests;

        if(method.equals("GET")) {

            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/student.twig");
            JtwigModel model = JtwigModel.newModel();

            User student = userService.getUserByCookie(httpExchange.getRequestHeaders().get("Cookie").get(0));

                artifacts = userService.getUserArtifacts(student.getId());
            quests = userService.getUserQuests(student.getId());

            model.with("name", student.getName());
            model.with("surname", student.getSurname());
            model.with("email", student.getEmail());
            model.with("phone", student.getPhoneNumber());
            //System.out.println(session);

            response = template.render(model);

            httpExchange.sendResponseHeaders(200, response.getBytes().length);
        }

        if (method.equals("POST")) {

        }
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

}
//Create table with user item
//Show Artifacts on User page
//Show User page
//Fix user page