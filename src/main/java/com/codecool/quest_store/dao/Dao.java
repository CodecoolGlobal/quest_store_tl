package com.codecool.quest_store.dao;

public interface DAO<T> {
    void create(T item) throws DaoException;
    void update(T item) throws DaoException;
}
