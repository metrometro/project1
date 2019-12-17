package com.epam.finalproject.dao;

import com.epam.finalproject.entity.Diet;
import com.epam.finalproject.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface DietDao {

    static Logger logger = LogManager.getLogger();

    List<Diet> findAll() throws DaoException;
    List<Diet> findUserDiet(String userLogin) throws DaoException;
    boolean createOrder(String userLogin, String dietType) throws DaoException;
    boolean deleteChosenDiet(String userLogin, String[] diet) throws DaoException;
    boolean updateDietOrder(String userLogin, String dietType) throws DaoException;
    boolean create(Diet diet) throws DaoException;
    boolean deleteDiets(String[] diets) throws DaoException;

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