package com.epam.finalproject.dao;

import com.epam.finalproject.entity.Entity;
import com.epam.finalproject.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface Dao <K, T extends Entity> {

    static Logger logger = LogManager.getLogger();

//    List<T> findAll() throws DaoException;
//    T findById(K id) throws DaoException;
//    boolean delete(K id) throws DaoException;
//    boolean create(T t) throws DaoException;
//    T update(T t);

    default void close(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    default void close(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    default void close(ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            logger.error(e);
        }
    }
}