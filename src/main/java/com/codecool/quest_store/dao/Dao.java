package com.codecool.quest_store.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface Dao<T> {
    void create(T thing) throws DaoException;
    void update(T thing) throws DaoException;
    T extractFromResultSet(ResultSet resultSet) throws DaoException;


    default List<T> getListByResultSet(ResultSet resultSet) throws DaoException{
        List<T> itemsList = new ArrayList<>();

        try{
            while (resultSet.next()){
                itemsList.add(extractFromResultSet(resultSet));
            }
        } catch (SQLException e){
            throw new DaoException("failed to convert result set into list", e);
        }
        return itemsList;
    }
}
