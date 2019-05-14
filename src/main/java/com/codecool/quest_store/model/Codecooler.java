package com.codecool.quest_store.model;

import java.util.ArrayList;
import java.util.List;

public class Codecooler extends User {

    private List<Item> quests;
    private List<Item> artifacts;
    private int coins;//?
    private int level;//?


    public Codecooler(UserBuilder builder) {
        super(builder);
        quests = new ArrayList<>();
        artifacts = new ArrayList<>();
    }

    public List<Item> getQuests() {
        return quests;
    }

    public List<Item> getArtifacts() {
        return artifacts;
    }
}
