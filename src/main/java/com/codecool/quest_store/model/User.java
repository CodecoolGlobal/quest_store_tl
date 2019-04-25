package com.codecool.quest_store.model;

public class User {
    private int id;
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private String password;
    private String photo;
    private int typeId;
    private int roomId;
    private int teamId;


    public User(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.surname = builder.surname;
        this.phoneNumber = builder.phoneNumber;
        this.email = builder.email;
        this.password = builder.password;
        this.photo = builder.photo;
        this.typeId = builder.typeId;
        this.roomId = builder.roomId;
        this.teamId = builder.teamId;
    }

    public int getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoto() {
        return photo;
    }

    public int getTypeId() {
        return typeId;
    }

    public int getRoomId() {
        return roomId;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public static class Builder {
        private int id;
        private String name;
        private String surname;
        private String phoneNumber;
        private String email;
        private String password;
        private String photo;
        private int typeId;
        private int roomId;
        private int teamId;

        public Builder withId(int id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder withPhoto(String photo) {
            this.photo = photo;
            return this;
        }

        public Builder withTypeId(int typeId) {
            this.typeId = typeId;
            return this;
        }

        public Builder withRoomId(int roomId) {
            this.roomId = roomId;
            return this;
        }

        public Builder withTeamId(int teamId) {
            this.teamId = teamId;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
