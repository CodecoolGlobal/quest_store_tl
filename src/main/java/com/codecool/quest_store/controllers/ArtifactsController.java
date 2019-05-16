package com.codecool.quest_store.controllers;

import com.codecool.quest_store.model.Item;
import com.codecool.quest_store.model.User;
import com.codecool.quest_store.service.ArtifactsService;
import com.codecool.quest_store.service.ServiceUtility;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.IOException;
import java.util.List;

public class ArtifactsController implements HttpHandler {

    private static final String ARTIFACT_CONTEXT_PATH = "/artifacts";
    private ArtifactsService artifactsService;
    private User activeUser;

    public ArtifactsController() {
        this.artifactsService = new ArtifactsService();
        this.activeUser = new User.UserBuilder()
                .withId(3).build();
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {


        String method = httpExchange.getRequestMethod();
        if (method.equals("GET")){
            renderArtifacts(httpExchange);
        } else if (method.equals("POST")){
            String response = artifactsService.respondToPostMethod(httpExchange, activeUser);
            ServiceUtility.redirectToContext(httpExchange, response, ARTIFACT_CONTEXT_PATH);
        }
    }

    private void renderArtifacts(HttpExchange httpExchange) throws IOException {
        List<Item> normalArtifacts = artifactsService.getNormalArtifacts();
        List<Item> magicArtifacts = artifactsService.getMagicArtifacts();

        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/artifacts.twig");
        JtwigModel model = JtwigModel.newModel();

        model.with("normal_artifacts", normalArtifacts);
        model.with("magic_artifacts", magicArtifacts);

        String response = template.render(model);

        ServiceUtility.sendResponse(httpExchange, response);
    }
}
