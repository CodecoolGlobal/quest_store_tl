package com.codecool.quest_store.model;

public class Room {

    private String roomName;
    private int id;

    public Room(String roomName, int id) {
        this.roomName = roomName;
        this.id = id;
    }

    public Room(String roomName) {
        this.roomName = roomName;
    }

    public int getId() {
        return id;
    }

    public String getRoomName() {
        return roomName;
    }
}
