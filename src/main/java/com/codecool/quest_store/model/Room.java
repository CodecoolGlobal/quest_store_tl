package com.codecool.quest_store.model;

public class Room {

    private int id;
    private String roomName;

    public Room(int id, String roomName) {
        this.id = id;
        this.roomName = roomName;
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
