package com.codecool.quest_store.model;

public enum UserDefaultPhoto {

    CREEPY_GUY("../static/assets/images/user-icons/creepy-guy.png"),
    MENTOR("../static/assets/images/user-icons/mentor.png"),
    CODECOOLER("../static/assets/images/user-icons/codecooler.png");

    private String userPhoto;

    private UserDefaultPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public String getUserPhoto() {
        return this.userPhoto;
    }
}

