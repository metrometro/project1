package com.epam.finalproject.dao.impl;

import com.epam.finalproject.dao.ColumnName;
import com.epam.finalproject.dao.ExerciseDao;
import com.epam.finalproject.pool.ConnectionPool;
import com.epam.finalproject.entity.Exercise;
import com.epam.finalproject.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExerciseDaoImpl implements ExerciseDao {

    private static Logger logger = LogManager.getLogger();

    private final static String SQL_SELECT_ALL_EXERCISES = "SELECT * FROM exercise";
    private final static String SQL_CREATE_EXERCISE = "INSERT INTO exercise(exercise_type) VALUES (?)";
    private final static String SQL_SELECT_PAID_USERS = "SELECT * FROM paid_users WHERE user_login  = ?";
    private final static String SQL_CREATE_EXERCISE_ORDER = "INSERT INTO exercise_order (user_login, exercise_type)" +
            " VALUES (?, ?)";
    private final static String SQL_SELECT_ALL_USER_EXERCISE = "SELECT exercise_type FROM exercise_order" +
            " WHERE user_login = ?";
    private final static String SQL_DELETE_EXERCISES_ORDER = "DELETE FROM exercise_order WHERE user_login = ?" +
            " AND exercise_type = ?";
    private final static String SQL_DELETE_USER_EXERCISE_ORDER = "DELETE FROM exercise_order WHERE user_login = ?";
    private final static String SQL_DELETE_EXERCISES = "DELETE FROM exercise WHERE exercise_type = ?";

    @Override
    public boolean deleteExercise(String[] exercises) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_DELETE_EXERCISES);
            for (int i = 0; i < exercises.length; i++) {
                statement.setString(1, exercises[i]);
                statement.executeUpdate();
            }
            return true;
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        } finally {
            close(statement);
            close(connection);
        }
    }

    @Override
    public boolean updateExerciseOrder(String userLogin, String[] exercises) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(SQL_DELETE_USER_EXERCISE_ORDER);
            statement.setString(1, userLogin);
            statement.executeUpdate();
            statement = connection.prepareStatement(SQL_CREATE_EXERCISE_ORDER);
            for (int i = 0; i < exercises.length; i++) {
                statement.setString(1, userLogin);
                statement.setString(2, exercises[i]);
                statement.executeUpdate();
            }
            connection.commit();
            return true;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                logger.error(ex);
            }
            logger.error(e);
            throw new DaoException(e);
        } finally {
            try {
                if (connection != null) {
                    connection.setAutoCommit(true);
                }
            } catch (SQLException e) {
                logger.error(e);
            }
            close(statement);
            close(connection);
        }
    }

    @Override
    public boolean deleteUserOrder(String userLogin) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_DELETE_USER_EXERCISE_ORDER);
            statement.setString(1, userLogin);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        } finally {
            close(statement);
            close(connection);
        }
    }

    @Override
    public boolean deleteChosenExercises(String userLogin, String[] exercises) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_DELETE_EXERCISES_ORDER);
            for (int i = 0; i < exercises.length; i++) {
                statement.setString(1, userLogin);
                statement.setString(2, exercises[i]);
                statement.executeUpdate();
            }
            return true;
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        } finally {
            close(statement);
            close(connection);
        }
    }



    @Override
    public List<Exercise> findUserExercises(String userLogin) throws DaoException {
        List<Exercise> exercises = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_SELECT_ALL_USER_EXERCISE);
            statement.setString(1, userLogin);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Exercise exercise = new Exercise();
                exercise.setExerciseType(resultSet.getString(ColumnName.EXERCISE_TYPE));
                exercises.add(exercise);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        } finally {
            close(resultSet);
            close(statement);
            close(connection);
        }
        return exercises;
    }

    @Override
    public boolean createOrder(String userLogin, String[] exercises) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(SQL_CREATE_EXERCISE_ORDER);

            for (int i = 0; i < exercises.length; i++) {
                statement.setString(1, userLogin);
                statement.setString(2, exercises[i]);
                statement.executeUpdate();
            }

            statement = connection.prepareStatement(SQL_SELECT_PAID_USERS,
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            statement.setString(1, userLogin);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                resultSet.updateBoolean(ColumnName.ASSIGNED_EXERCISES, true);
                resultSet.updateRow();
            }
            connection.commit();
            return true;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                logger.error(ex);
            }
            logger.error(e);
            throw new DaoException(e);
        } finally {
            try {
                if (connection != null) {
                    connection.setAutoCommit(true);
                }
            } catch (SQLException e) {
                logger.error(e);
            }
            close(resultSet);
            close(statement);
            close(connection);
        }
    }

    @Override
    public List<Exercise> findNotSame(String[] currentExercises) throws DaoException {
        List<Exercise> exercises = new ArrayList<Exercise>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_SELECT_ALL_EXERCISES);
            while (resultSet.next()) {
                Exercise exercise = new Exercise();
                exercise.setExerciseId(resultSet.getInt(ColumnName.EXERCISE_ID));
                exercise.setExerciseType(resultSet.getString(ColumnName.EXERCISE_TYPE));
                exercises.add(exercise);
            }
            if (currentExercises == null) {
                return exercises;
            }
            for (int i = 0; i < currentExercises.length; i++) {
                String exerciseType = currentExercises[i];
                exercises.removeIf(e -> (e.getExerciseType()).equals(exerciseType));
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        } finally {
            close(resultSet);
            close(statement);
            close(connection);
        }
        return exercises;

    }

    @Override
    public List<Exercise> findAllExercises() throws DaoException {
        List<Exercise> exercises = new ArrayList<Exercise>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_SELECT_ALL_EXERCISES);
            while (resultSet.next()) {
                Exercise exercise = new Exercise();
                exercise.setExerciseId(resultSet.getInt(ColumnName.EXERCISE_ID));
                exercise.setExerciseType(resultSet.getString(ColumnName.EXERCISE_TYPE));
                exercises.add(exercise);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        } finally {
            close(resultSet);
            close(statement);
            close(connection);
        }
        return exercises;
    }


    @Override
    public boolean create(Exercise exercise) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_CREATE_EXERCISE);
            statement.setString(1, exercise.getExerciseType());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        } finally {
            close(statement);
            close(connection);
        }
    }
}