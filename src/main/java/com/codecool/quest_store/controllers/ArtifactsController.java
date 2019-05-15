package com.codecool.quest_store.controllers;

import com.codecool.quest_store.model.Item;
import com.codecool.quest_store.model.User;
import com.codecool.quest_store.service.ArtifactsService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.List;

public class ArtifactsController implements HttpHandler {

    private ArtifactsService artifactsService;
    private User activeUser;

    public ArtifactsController() {
        this.artifactsService = new ArtifactsService();
//        this.activeUser =
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String method = httpExchange.getRequestMethod();
        if (method.equals("GET")){
            renderArtifacts(httpExchange);
        } else if (method.equals("POST")){
            String response = artifactsService.respondToPostMethod(httpExchange, activeUser);
            redirectToArtifactContext(httpExchange, response);
        }
    }

    private void redirectToArtifactContext(HttpExchange httpExchange, String response) throws IOException {
        httpExchange.getResponseHeaders().set("Location", "/artifacts");

        httpExchange.sendResponseHeaders(302, response.getBytes().length);

        OutputStream outputStream = httpExchange.getResponseBody();
        outputStream.write(response.getBytes(Charset.forName("UTF-8")));
        outputStream.close();
    }



    private void renderArtifacts(HttpExchange httpExchange) throws IOException {
        List<Item> allNormalArtifacts = artifactsService.getNormalArtifacts();
        List<Item> allMagicArtifacts = artifactsService.getMagicArtifacts();

        JtwigTemplate template = JtwigTemplate.classpathTemplate("templates/artifacts.twig");
        JtwigModel model = JtwigModel.newModel();

        model.with("normal_artifacts", allNormalArtifacts);
        model.with("magic_artifacts", allMagicArtifacts);

        String response = template.render(model);

        sendResponse(httpExchange, response);
    }

    private void sendResponse(HttpExchange httpExchange, String response) throws IOException {
        httpExchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }


}
