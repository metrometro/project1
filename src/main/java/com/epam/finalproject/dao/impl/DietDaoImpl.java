package com.epam.finalproject.dao.impl;

import com.epam.finalproject.dao.ColumnName;
import com.epam.finalproject.dao.DietDao;
import com.epam.finalproject.entity.Diet;
import com.epam.finalproject.exception.DaoException;
import com.epam.finalproject.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DietDaoImpl implements DietDao {

    private static Logger logger = LogManager.getLogger();

    private final static String SQL_SELECT_ALL_DIETS = "SELECT * FROM diet";
    private final static String SQL_CREATE_DIET_ORDER = "INSERT INTO diet_order(user_login, diet_type) VALUES (?, ?)";
    private final static String SQL_SELECT_PAID_USERS = "SELECT * FROM paid_users WHERE user_login  = ?";
    private final static String SQL_SELECT_USER_DIET = "SELECT diet_type FROM diet_order WHERE user_login = ?";
    private final static String SQL_DELETE_DIET_ORDER = "DELETE FROM diet_order WHERE user_login = ? AND diet_type = ?";
    private final static String SQL_DELETE_USER_ORDER = "DELETE FROM diet_order WHERE user_login = ?";
    private final static String SQL_CREATE_DIET = "INSERT INTO diet(diet_type) VALUES (?)";
    private final static String SQL_DELETE_DIETS = "DELETE FROM diet WHERE diet_type = ?";


    @Override
    public boolean deleteDiets(String[] diets) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_DELETE_DIETS);
            for (int i = 0; i < diets.length; i++) {
                statement.setString(1, diets[i]);
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
    public boolean create(Diet diet) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_CREATE_DIET);
            statement.setString(1, diet.getDietType());
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
    public boolean updateDietOrder(String userLogin, String dietType) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(SQL_DELETE_USER_ORDER);
            statement.setString(1, userLogin);
            statement.executeUpdate();
            statement = connection.prepareStatement(SQL_CREATE_DIET_ORDER);
            statement.setString(1, userLogin);
            statement.setString(2, dietType);
            statement.executeUpdate();
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
    public boolean deleteChosenDiet(String userLogin, String[] diet) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_DELETE_DIET_ORDER);
            for (int i = 0; i < diet.length; i++) {
                statement.setString(1, userLogin);
                statement.setString(2, diet[i]);
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
    public boolean createOrder(String userLogin, String dietType) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(SQL_CREATE_DIET_ORDER);
            statement.setString(1, userLogin);
            statement.setString(2, dietType);
            statement.executeUpdate();
            statement = connection.prepareStatement(SQL_SELECT_PAID_USERS,
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            statement.setString(1, userLogin);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                resultSet.updateBoolean(ColumnName.ASSIGNED_DIET, true);
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
    public List<Diet> findUserDiet(String userLogin) throws DaoException {
        List<Diet> diets = new ArrayList<>();
        Diet diet = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_SELECT_USER_DIET);
            statement.setString(1, userLogin);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                diet = new Diet();
                diet.setDietType(resultSet.getString(ColumnName.DIET_TYPE));
                diets.add(diet);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        } finally {
            close(resultSet);
            close(statement);
            close(connection);
        }
        return diets;
    }

    @Override
    public List<Diet> findAll() throws DaoException {
        List<Diet> diets = new ArrayList<Diet>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_SELECT_ALL_DIETS);

            while (resultSet.next()) {
                Diet di = new Diet();
                di.setDietId(resultSet.getInt(ColumnName.DIET_ID));
                di.setDietType(resultSet.getString(ColumnName.DIET_TYPE));
                diets.add(di);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        } finally {
            close(resultSet);
            close(statement);
            close(connection);
        }
        return diets;
    }
}