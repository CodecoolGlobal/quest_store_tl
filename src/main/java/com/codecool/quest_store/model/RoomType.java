package com.codecool.quest_store.model;

public enum RoomType {

    PROGBASIC(1),
    JAVA(2),
    WEB(3),
    ADVANCED(4);

    private int roomType;

    private RoomType(int roomType) {
        this.roomType = roomType;
    }

    public int getRoomType() {
        return this.roomType;
    }
}