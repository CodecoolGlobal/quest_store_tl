package com.codecool.quest_store.controllers;

import com.codecool.quest_store.model.Item;
import com.codecool.quest_store.service.ArtifactsService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class ArtifactsController implements HttpHandler {

    private ArtifactsService artifactsService;
    private int userId;

    public ArtifactsController() {
        this.artifactsService = new ArtifactsService();
        this.userId = 1; //NEED TO POINT TO A METHOD
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        List<Item> allNormalArtifacts = artifactsService.getNormalArtifacts();
        List<Item> allMagicArtifacts = artifactsService.getMagicArtifacts();

        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/artifacts.twig");
        JtwigModel model = JtwigModel.newModel();

        model.with("normal_artifacts", allNormalArtifacts);
        model.with("magic_artifacts", allMagicArtifacts);

        String response = template.render(model);

        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }



}
