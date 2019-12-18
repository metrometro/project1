package com.epam.finalproject.dao;

import com.epam.finalproject.entity.Exercise;
import com.epam.finalproject.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface ExerciseDao {

    static Logger logger = LogManager.getLogger();

    List<Exercise> findAllExercises() throws DaoException;
    List<Exercise> findNotSame(String[] currentExercises) throws DaoException;
    List<Exercise> findUserExercises(String login) throws DaoException;
    boolean create(Exercise exercise) throws DaoException;
    boolean createOrder(String userLogin, String[] exercises) throws DaoException;
    boolean deleteChosenExercises(String userLogin, String[] exercises) throws DaoException;
    boolean deleteUserOrder(String userLogin) throws DaoException;
    boolean updateExerciseOrder(String userLogin, String[] exercises) throws DaoException;
    boolean deleteExercise(String[] exercises) throws DaoException;

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