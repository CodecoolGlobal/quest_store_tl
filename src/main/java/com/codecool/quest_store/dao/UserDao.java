package com.codecool.quest_store.dao;

import com.codecool.quest_store.model.User;

import java.util.List;

public interface UserDao {
    List<User> getUsersByType(int userType);
    void createUser(String name, String surname, String phoneNumber, String email,
               String password, String photo, int typeId, int roomId, int teamId);
    void updateUserEmail(User user, String email);
    void updateUserRoom(User user, int room);
}
