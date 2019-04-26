package com.codecool.quest_store.model;

import java.util.ArrayList;
import java.util.List;

public class Team {

    private int id;
    private List<Codecooler> codecoolers;
    private List<Item> artifacts;
    private List<Item> quests;
    private String name;
    private String projectName;

    public Team() {
        codecoolers = new ArrayList<>();
        artifacts = new ArrayList<>();
        quests = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public List<Codecooler> getCodecoolers() {
        return codecoolers;
    }

    public List<Item> getArtifacts() {
        return artifacts;
    }

    public List<Item> getQuests() {
        return quests;
    }

    public String getName() {
        return name;
    }

    public String getProjectName() {
        return projectName;
    }
}