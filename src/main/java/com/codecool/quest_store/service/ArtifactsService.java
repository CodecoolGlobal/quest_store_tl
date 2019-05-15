package com.codecool.quest_store.service;

import com.codecool.quest_store.controllers.ControllerUtility;
import com.codecool.quest_store.dao.DaoException;
import com.codecool.quest_store.dao.ItemDao;
import com.codecool.quest_store.dao.ItemDaoImpl;
import com.codecool.quest_store.model.Item;
import com.codecool.quest_store.model.User;
import com.sun.net.httpserver.HttpExchange;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ArtifactsService {

    private ItemDao itemDAO;
    private static final int NORMAL_ARTIFACT_TYPE = 1;
    private static final int MAGIC_ARTIFACT_TYPE = 2;


    public ArtifactsService() {
        this.itemDAO = new ItemDaoImpl();
    }

    public List<Item> getNormalArtifacts(){
      return getAllArtifactsOfType(NORMAL_ARTIFACT_TYPE);
    }


    public List<Item> getMagicArtifacts(){
        return getAllArtifactsOfType(MAGIC_ARTIFACT_TYPE);
    }

    private List<Item> getAllArtifactsOfType(int artifactType) {
        List<Item> artifactsOfType = new ArrayList<>();
        List<Item> allArtifacts = new ArrayList<>();
        try {
            allArtifacts = itemDAO.getAllArtifacts();
        } catch (DaoException e) {
            e.printStackTrace();
        }
        for(Item artifact : allArtifacts){
            if (artifact.getType() == artifactType) artifactsOfType.add(artifact);
        }
        return artifactsOfType;
    }

    public Item getItemById(int id) throws DaoException{
            return itemDAO.getItemById(id);
    }

    public String respondToPostMethod(HttpExchange httpExchange, User user) throws IOException{
        String postData = new BufferedReader(new InputStreamReader(httpExchange.getRequestBody())).readLine();
        Map<String, String> postMap = ControllerUtility.parseFormData(postData);
        System.out.println(postMap);
        int artifactId = Integer.parseInt(postMap.get("artifactId"));
        try {
            handleArtifactPurchase(user, getItemById(artifactId));
        } catch (DaoException e) {
            e.printStackTrace();
        } return postMap.toString();
    }

    private void handleArtifactPurchase(User user, Item artifact) {
        if (artifact.getType() == 1 ){
            handleIndividualPurchase(user, artifact);
        } else {
            handleTeamPurchase(); //descoped from this sprint
        }
    }


    private void handleIndividualPurchase(User user, Item artifact) {

    }

    private void handleTeamPurchase() {
    }
}
