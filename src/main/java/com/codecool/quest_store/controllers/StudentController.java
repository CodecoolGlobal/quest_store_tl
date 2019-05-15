package com.codecool.quest_store.controllers;

import com.codecool.quest_store.dao.DaoException;
import com.codecool.quest_store.dao.SessionDaoImpl;
import com.codecool.quest_store.model.User;
import com.codecool.quest_store.service.LoginService;
import com.codecool.quest_store.service.ServiceUtility;
import com.codecool.quest_store.service.StudentService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.IOException;
import java.io.OutputStream;

public class StudentController implements HttpHandler {

    LoginService login;
    ServiceUtility utility;
    StudentService studentService;

    public StudentController() {
        login = new LoginService();
        utility = new ServiceUtility();
        studentService = new StudentService();
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
                    .replace("\"", ""));


            User student = studentService.getUser(studentService.getUserId(session));
            model.with("name", student.getName());
            model.with("surname", student.getSurname());
            model.with("email", student.getEmail());
            model.with("phone", student.getPhoneNumber());
            System.out.println(session);

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