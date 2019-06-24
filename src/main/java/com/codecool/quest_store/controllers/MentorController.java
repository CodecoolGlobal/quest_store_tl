package com.codecool.quest_store.controllers;

import java.util.Map;

import java.io.IOException;

import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import com.codecool.quest_store.service.EmployeeService;
import com.codecool.quest_store.service.ItemService;
import com.codecool.quest_store.service.MentorService;
import com.codecool.quest_store.service.ServiceUtility;
import com.codecool.quest_store.service.UserService;

import com.codecool.quest_store.model.UserDefaultPhoto;

public class MentorController implements HttpHandler {

    private EmployeeService employeeService;
    private MentorService mentorService;
    private UserService userService;
    private ItemService itemService;

    public MentorController() {
        employeeService = new EmployeeService();
        mentorService = new MentorService();
        userService = new UserService();
        itemService = new ItemService();
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        String method = httpExchange.getRequestMethod();

        if (method.equals("GET")) {
            userService.getUserByCookie(httpExchange.getRequestHeaders().get("Cookie").get(0));
            renderMentor(httpExchange);
        }

        if (method.equals("POST")) {
            handlePOST(httpExchange);
        }
    }

    private void handlePOST(HttpExchange httpExchange) throws IOException {
        Map<String, String> inputs = ServiceUtility.getPOSTInputs(httpExchange);

        for (Map.Entry<String, String> entry : inputs.entrySet()) {

            if (entry.getKey().contains("recruit-student-button")) {
                handleCreateCodecooler(inputs);

            } else if (entry.getKey().contains("create-quest-button")) {
                handleCreateQuest(inputs);

            } else if (entry.getKey().contains("create-artifact-button")) {
                handleCreateArtifact(inputs);
            }
        }

        ServiceUtility.redirectToContext(httpExchange, "", "/mentor");
    }

    private void renderMentor(HttpExchange httpExchange) throws IOException {
        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/mentor.twig");
        JtwigModel model = JtwigModel.newModel();
        String response = template.render(model);
        ServiceUtility.sendResponse(httpExchange, response);
    }

    private void handleCreateCodecooler(Map<String, String> inputs) {
        String name = inputs.get("cc-name");
        String surname = inputs.get("cc-surname");
        String email = inputs.get("cc-email");
        int userType = userService.getUserTypes().get("Codecooler");
        employeeService.createUser(name, surname, email, userType, UserDefaultPhoto.CODECOOLER);

    }

    private void handleCreateQuest(Map<String, String> inputs) {
        String title = inputs.get("quest-name");
        String task = inputs.get("quest-task");
        int price = Integer.parseInt(inputs.get("quest-coins"));
        int itemType = getQuestTypeFromInput(inputs);
        mentorService.createItem(title, task, price, itemType);
    }

    private int getQuestTypeFromInput(Map<String, String> inputs) {
        String itemType = inputs.get("quest-type-choice");
        if (itemType.equals("Basic")) {
            return itemService.getItemTypes().get("Basic");
        } else {
            return itemService.getItemTypes().get("Extra");
        }
    }

    private void handleCreateArtifact(Map<String, String> inputs) {
        String title = inputs.get("artifact-name");
        String description = inputs.get("artifact-description");
        int price = Integer.parseInt(inputs.get("artifact-coins"));
        int itemType = getArtifactTypeFromInput(inputs);
        mentorService.createItem(title, description, price, itemType);
    }

    private int getArtifactTypeFromInput(Map<String, String> inputs) {
        String itemType = inputs.get("artifact-type-choice");
        if (itemType.equals("Normal")) {
            return itemService.getItemTypes().get("Normal");
        } else {
            return itemService.getItemTypes().get("Magic");
        }
    }
}