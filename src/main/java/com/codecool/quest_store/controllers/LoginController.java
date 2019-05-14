package com.codecool.quest_store.controllers;
import com.codecool.quest_store.model.User;

import com.codecool.quest_store.service.LoginService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.*;
import java.net.HttpCookie;
import java.util.Map;

public class LoginController implements HttpHandler {

    LoginService login;

    public LoginController() {
        login = new LoginService();
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String response = "";
        String method = httpExchange.getRequestMethod();
        System.out.println(method);

        if (method.equals("GET")) {
            JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/login.twig");
            JtwigModel model = JtwigModel.newModel();

            response = template.render(model);
            httpExchange.sendResponseHeaders(200, response.length());
        }

        if (method.equals("POST")) {

            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();
            Map inputs = login.parseFormData(formData);

            String nextUrl = "";

            User user = login.getUser(inputs.get("name").toString(), inputs.get("password").toString());
            int userType = user.getTypeId();

            if (!user.equals(null) && userType == 1) {
                nextUrl = "http://localhost:8000/student";
            }
            if (!user.equals(null) && userType == 2) {
                nextUrl = "http://localhost:8000/mentor";
            }
            if (!user.equals(null) && userType == 3) {
                nextUrl = "http://localhost:8000/creepy-guy";
            }

            int session = login.generateNewSessionId();

            System.out.println(session);

            login.getSession(session, user.getId());

            httpExchange.getResponseHeaders().add("Location", nextUrl);
            httpExchange.getResponseHeaders().add("Set-Cookie",
                    new HttpCookie("sessionId", Integer.toString(session)).toString());

            httpExchange.sendResponseHeaders(302, response.length());
        }
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
