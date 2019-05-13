package com.codecool.quest_store.model;

public class Team {

    private final int id;
    private String teamName;
    private String projectName;

    public Team(TeamBuilder teamBuilder) {
        this.id = teamBuilder.id;
        this.teamName = teamBuilder.teamName;
        this.projectName = teamBuilder.projectName;
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

    public static class TeamBuilder{
    private int id;
    private String teamName;
    private String projectName;

    public TeamBuilder withId(int id){
        this.id = id;
        return this;
    }

    public TeamBuilder  withTeamName(String teamName){
        this.teamName = teamName;
        return this;
    }

    public TeamBuilder withProjectName(String projectName){
        this.projectName = projectName;
        return this;
    }

    public Team build(){
        return new Team(this);
    }

    }
}