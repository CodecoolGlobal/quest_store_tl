package com.codecool.quest_store.service;

import com.codecool.quest_store.dao.DaoException;
import com.codecool.quest_store.dao.ItemDao;
import com.codecool.quest_store.dao.ItemDaoImpl;
import com.codecool.quest_store.model.Item;

import java.util.ArrayList;
import java.util.List;

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
}
