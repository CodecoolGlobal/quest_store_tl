package com.codecool.quest_store.dao;

public class DaoException extends Exception {

    public DaoException(Throwable error){
        super(error);
    }

    public DaoException(String errorMessage){
        super(errorMessage);
    }

    public DaoException(String errorMessage, Throwable error){
        super(errorMessage, error);
    }
}
