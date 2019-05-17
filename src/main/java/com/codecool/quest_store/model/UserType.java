package com.codecool.quest_store.model;

public enum UserType {

    CREEPY_GUY(3),
    MENTOR(2),
    CODECOOLER(1);

    private int userType;

    private UserType(int userType) {
        this.userType = userType;
    }

    public int getUserType() {
        return this.userType;
    }
}