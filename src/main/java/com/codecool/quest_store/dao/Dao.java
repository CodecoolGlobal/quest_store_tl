package com.codecool.quest_store.dao;

public interface Dao<T> {
    void create(T thing) throws DaoException;
    void update(T thing) throws DaoException;
}
