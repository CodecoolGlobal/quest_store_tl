package com.codecool.quest_store.model;

import java.util.ArrayList;
import java.util.List;

public class Mentor extends User {

    private List<Codecooler> codecoolers;
    private List<Item> questsToReview;
    private List<Item> artifactsToRiview;

    public Mentor(Builder builder) {
        super(builder);
        codecoolers = new ArrayList<>();
        questsToReview = new ArrayList<>();
        artifactsToRiview = new ArrayList<>();
    }

    public List<Codecooler> getCodecoolers() {
        return codecoolers;
    }

    public List<Item> getQuestsToReview() {
        return questsToReview;
    }

    public List<Item> getArtifactsToRiview() {
        return artifactsToRiview;
    }
}
