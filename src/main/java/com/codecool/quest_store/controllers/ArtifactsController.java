package com.codecool.quest_store.controllers;

import com.codecool.quest_store.model.Item;
import com.codecool.quest_store.model.User;
import com.codecool.quest_store.service.ArtifactsService;
import com.codecool.quest_store.service.ServiceUtility;
import com.codecool.quest_store.service.UserService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.IOException;
import java.util.List;

public class ArtifactsController implements HttpHandler {

    private static final String ARTIFACT_CONTEXT_PATH = "/artifacts";
    private ArtifactsService artifactsService;
    private UserService userService;

    public ArtifactsController() {
        this.artifactsService = new ArtifactsService();
        this.userService = new UserService();
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        User activeUser = userService.getUserByCookie(httpExchange.getRequestHeaders().get("Cookie").get(0));

        String method = httpExchange.getRequestMethod();
        if (method.equals("GET")){
            renderArtifacts(httpExchange, activeUser);
        } else if (method.equals("POST")){
            String response = artifactsService.respondToPostMethod(httpExchange, activeUser);
            ServiceUtility.redirectToContext(httpExchange, response, ARTIFACT_CONTEXT_PATH);
        }
    }

    private void renderArtifacts(HttpExchange httpExchange, User user) throws IOException {
        List<Item> normalArtifacts = artifactsService.getNormalArtifacts();
        List<Item> magicArtifacts = artifactsService.getMagicArtifacts();
        int discount = artifactsService.getDiscount();


        JtwigTemplate template;
        if (user.getTypeId() == 1) {
            template = JtwigTemplate.classpathTemplate("templates/student-artifacts.twig");
        } else {
            template = JtwigTemplate.classpathTemplate("templates/mentor-artifacts.twig");
        }

        JtwigModel model = JtwigModel.newModel();

        model.with("normal_artifacts", normalArtifacts);
        model.with("magic_artifacts", magicArtifacts);
        model.with("discount", discount);

        String response = template.render(model);

        ServiceUtility.sendResponse(httpExchange, response);
        }
}
