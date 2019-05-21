package com.codecool.quest_store.controllers;
import com.codecool.quest_store.model.User;

import com.codecool.quest_store.service.LoginService;
import com.codecool.quest_store.service.ServiceUtility;
import com.codecool.quest_store.service.UserService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.*;
import java.net.HttpCookie;
import java.util.Map;

public class LoginController implements HttpHandler {

    private LoginService login;
    private ServiceUtility utility;
    private UserService userService;
    private static final int STUDENT = 1;
    private static final int MENTOR = 2;
    private static final int CREEPUGUY = 3;

    public LoginController() {
        login = new LoginService();
        utility = new ServiceUtility();
        userService = new UserService();
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
            httpExchange.sendResponseHeaders(200, response.getBytes().length);

            userService.deleteSession(httpExchange.getRequestHeaders().get("Cookie").get(0));
        }

        if (method.equals("POST")) {

            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();
            Map inputs = utility.parseData(formData, ServiceUtility.AND);

            String nextUrl = "";

            User user = login.getUser(inputs.get("name").toString(), inputs.get("password").toString());
            int userType = user.getTypeId();

            if (!user.equals(null) && userType == STUDENT) {
                nextUrl = "http://localhost:8000/student";
            }
            if (!user.equals(null) && userType == MENTOR) {
                nextUrl = "http://localhost:8000/mentor";
            }
            if (!user.equals(null) && userType == CREEPUGUY) {
                nextUrl = "http://localhost:8000/creepy-guy";
            }

            int session = login.generateNewSessionId();

            login.createSession(session, user.getId());

            httpExchange.getResponseHeaders().add("Location", nextUrl);
            httpExchange.getResponseHeaders().add("Set-Cookie",
                    new HttpCookie("session", Integer.toString(session)).toString());

            httpExchange.sendResponseHeaders(302, response.getBytes().length);
        }
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
