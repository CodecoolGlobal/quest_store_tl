package com.codecool.quest_store.model;

public class Team {

    private int id;
    private String teamName;
    private String projectName;

    public Team(int id, String name, String projectName) {
        this.id = id;
        this.teamName = name;
        this.projectName = projectName;
    }

    public Team(String name, String projectName) {
        this.teamName = name;
        this.projectName = projectName;
    }

    public int getId() {
        return id;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getProjectName() {
        return projectName;
    }
}