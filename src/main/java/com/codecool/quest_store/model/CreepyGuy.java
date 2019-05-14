package com.codecool.quest_store.model;

import java.util.ArrayList;
import java.util.List;

public class CreepyGuy extends User{

    private List<Mentor> mentors;
    private List<Room> rooms;
    private List<Level> levels;


    public CreepyGuy(UserBuilder builder) {
        super(builder);
        mentors = new ArrayList<>();
        rooms = new ArrayList<>();
        levels = new ArrayList<>();
    }

    public List<Mentor> getMentors() {
        return mentors;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public List<Level> getLevels() {
        return levels;
    }

}
