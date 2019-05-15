package com.codecool.quest_store.controllers;

import com.codecool.quest_store.service.LoginService;
import com.codecool.quest_store.service.ServiceUtility;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.IOException;
import java.io.OutputStream;

public class StudentController implements HttpHandler {

    LoginService login;
    ServiceUtility utility;

    public StudentController() {
        login = new LoginService();
        utility = new ServiceUtility();
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String response = "";
        String method = httpExchange.getRequestMethod();
        System.out.println(method);

        if(method.equals("GET")) {

            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/student.twig");
            JtwigModel model = JtwigModel.newModel();

            String cookie = httpExchange.getRequestHeaders().get("Cookie").get(0);

            int session = Integer.valueOf(utility.parseData(cookie, ServiceUtility.SEMICOLON).get("session")
                    .replaceAll("\\D", ""));
            System.out.println(session);
            //model.with("name", getUserName(userId));
            response = template.render(model);
            httpExchange.sendResponseHeaders(200, 0);
        }
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
