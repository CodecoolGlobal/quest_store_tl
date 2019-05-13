package com.codecool.quest_store.model;

public class Room {

    private final int id;
    private String roomName;

    public Room(RoomBuilder roomBuilder) {
        this.id = roomBuilder.id;
        this.roomName = roomBuilder.roomName;
    }

    public int getId() {
        return id;
    }

    public String getRoomName() {
        return roomName;
    }

    public static class RoomBuilder {
        private int id;
        private String roomName;

        public RoomBuilder withId(int id) {
            this.id = id;
            return this;
        }

        public RoomBuilder withRoomName(String roomName) {
            this.roomName = roomName;
            return this;
        }

        public Room build() {
            return new Room(this);
        }
    }
}
