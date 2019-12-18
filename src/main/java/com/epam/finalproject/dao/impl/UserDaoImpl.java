package com.epam.finalproject.dao.impl;

import com.epam.finalproject.dao.ColumnName;
import com.epam.finalproject.pool.ConnectionPool;
import com.epam.finalproject.dao.UserDao;
import com.epam.finalproject.entity.User;
import com.epam.finalproject.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private static Logger logger = LogManager.getLogger();
    private final static String SQL_PERSONAL_TRAINER_ORDER = "INSERT INTO personal_trainer_order " +
            "(trainer_login, user_login) VALUES (?, ?)";
    private final static String SQL_SELECT_ALL_PAID_USERS = "SELECT users.first_name, users.last_name, users.login FROM" +
            " users LEFT JOIN paid_users ON users.login = paid_users.user_login WHERE paid_users.personal_trainer =" +
            " false AND users.role = 3 AND users.status = 1";
    private final static String SQL_SELECT_ALL_PAID_USERS_WITH_PERSONAL_TRAINER = "SELECT users.first_name, users.last_name," +
            " users.login FROM users LEFT JOIN paid_users ON users.login = paid_users.user_login WHERE " +
            "paid_users.personal_trainer = true AND paid_users.assigned_trainer = false";
    private final static String SQL_SELECT_ALL_USERS_WITHOUT_EXERCISES = "SELECT users.first_name, users.last_name, " +
            "users.login FROM users LEFT JOIN paid_users ON users.login = paid_users.user_login WHERE " +
            "paid_users.personal_trainer = false AND paid_users.assigned_exercises = false";
    private final static String SQL_SELECT_ALL_USERS_WITHOUT_DIET = "SELECT users.first_name, users.last_name, users.login" +
            " FROM users LEFT JOIN paid_users ON users.login = paid_users.user_login WHERE paid_users.personal_trainer =" +
            " false AND paid_users.assigned_diet = false";
    private final static String SQL_SELECT_ALL_USERS = "SELECT * FROM users WHERE role = 3 AND status = 1";
    private final static String SQL_SELECT_ALL_DELETED_USERS = "SELECT * FROM users WHERE role = 3 AND status = 0";
    private final static String SQL_SELECT_ALL_TRAINERS = "SELECT * FROM users WHERE role = 2 AND status = 1";
    private final static String SQL_EXIST_USER_LOGIN = "SELECT 1 FROM users WHERE login = ?";
    private final static String SQL_SELECT_USER = "SELECT * FROM users WHERE login = ?";
    private final static String SQL_CREATE_USER = "INSERT INTO users(first_name, last_name, login, password, email, " +
            "role, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private final static String SQL_CORRECT_PASSWORD_AND_LOGIN = "SELECT users.user_id, users.login, users.password," +
            " role.role, users.status FROM users LEFT JOIN role ON users.role = role.role_id WHERE login = ?";//
    private final static String SQL_SELECT_ALL_TRAINERS_USERS = "SELECT * FROM personal_trainer_order WHERE trainer_login = ?";
    private final static String SQL_SELECT_CARD = "SELECT * FROM cards WHERE first_code = ? AND second_code = ? AND" +
            " third_code = ? AND fourth_code = ? AND cvc = ?";
    private final static String SQL_SELECT_USER_BANK = "SELECT * FROM bank WHERE user_login = ?";
    private final static String SQL_INSERT_USER_BANK = "INSERT INTO bank (user_login, user_bank) VALUES (?, ?)";
    private final static String SQL_INSERT_TRAINING = "INSERT INTO paid_users(user_login, number_of_workouts, personal_trainer)" +
            " VALUES (?, ?, ?)";
    private final static String SQL_IS_USER_PAID = "SELECT * FROM paid_users WHERE user_login = ?";
    private final static String SQL_SELECT_PRICE = "SELECT * FROM price";
    private final static String SQL_SELECT_USER_DISCOUNT = "SELECT discounter FROM discount WHERE  cash <=" +
            " ((SELECT user_bank FROM bank WHERE user_login = ?) + ?)";
    private final static String SQL_SELECT_PAID_USERS = "SELECT * FROM paid_users WHERE user_login = ?";
    private final static String SQL_DELETE_PAID_USER = "DELETE FROM paid_users WHERE user_login = ?";
    private final static String SQL_DELETE_USER_EXERCISES_ORDER = "DELETE FROM diet_order WHERE user_login = ?";
    private final static String SQL_DELETE_USER_DIET_ORDER = "DELETE FROM exercise_order WHERE user_login = ?";
    private final static String SQL_DELETE_USER_PERSONAL_TRAINER_ORDER = "DELETE FROM personal_trainer_order WHERE user_login = ?";
    private final static String SQL_CREATE_COMMENT = "INSERT INTO comments(user_login, comment) VALUES (?, ?)";
    private final static String SQL_SELECT_USER_COMMENTS = "SELECT comment FROM comments WHERE user_login = ?";
    private final static String SQL_SELECT_USER_STATE = "SELECT paid_users.user_login, paid_users.number_of_workouts," +
            " personal_trainer_order.trainer_login FROM paid_users LEFT JOIN personal_trainer_order ON " +
            "paid_users.user_login = personal_trainer_order.user_login WHERE paid_users.user_login = ?";

    @Override
    public boolean makeUser(String userLogin) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_SELECT_USER,
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            statement.setString(1, userLogin);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                resultSet.updateInt(ColumnName.ROLE, 3);
                resultSet.updateRow();
            }
            return true;
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        } finally {
            close(resultSet);
            close(statement);
            close(connection);
        }
    }

    @Override
    public boolean makeTRainer(String userLogin) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_SELECT_USER,
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            statement.setString(1, userLogin);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                resultSet.updateInt(ColumnName.ROLE, 2);
                resultSet.updateRow();
            }
            return true;
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        } finally {
            close(resultSet);
            close(statement);
            close(connection);
        }
    }

    @Override
    public boolean restoreUser(String userLogin) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_SELECT_USER,
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            statement.setString(1, userLogin);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                resultSet.updateBoolean(ColumnName.STATUS, true);
                resultSet.updateRow();
            }
            return true;
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        } finally {
            close(resultSet);
            close(statement);
            close(connection);
        }
    }

    @Override
    public boolean deleteUser(String userLogin) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_SELECT_USER,
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            statement.setString(1, userLogin);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                resultSet.updateBoolean(ColumnName.STATUS, false);
                resultSet.updateRow();
            }
            return true;
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        } finally {
            close(resultSet);
            close(statement);
            close(connection);
        }
    }

    @Override
    public List<String> findUserStatus(String login) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<String> list = new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_SELECT_USER_STATE);
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                list.add(login);
                int numberOfWorkouts = resultSet.getInt(ColumnName.NUMBER_OF_WORKOUTS);
                String workouts = Integer.toString(numberOfWorkouts);
                list.add(workouts);
                String trainerLogin = resultSet.getString(ColumnName.TRAINER_LOGIN);
                list.add(trainerLogin);
            }
            return list;
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        } finally {
            close(resultSet);
            close(statement);
            close(connection);
        }
    }

    @Override
    public List<String> findCurrentUserComments(String userLogin) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<String> list = new ArrayList<>();
        String comment = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_SELECT_USER_COMMENTS);
            statement.setString(1, userLogin);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                comment = resultSet.getString(ColumnName.COMMENT);
                list.add(comment);
            }
            return list;
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        } finally {
            close(resultSet);
            close(statement);
            close(connection);
        }
    }

    @Override
    public boolean createComment(String userLogin, String comment) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_CREATE_COMMENT);
            statement.setString(1, userLogin);
            statement.setString(2, comment);
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
    public boolean markUsersVisit(String[] users) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int numberOfWorkout = 0;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            for (int i = 0; i < users.length; i++) {
                statement = connection.prepareStatement(SQL_SELECT_PAID_USERS,
                        ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                statement.setString(1, users[i]);
                resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    numberOfWorkout = resultSet.getInt(ColumnName.NUMBER_OF_WORKOUTS);
                    if (numberOfWorkout <= 1) {
                        statement = connection.prepareStatement(SQL_DELETE_PAID_USER);
                        statement.setString(1, users[i]);
                        statement.executeUpdate();
                        statement = connection.prepareStatement(SQL_DELETE_USER_EXERCISES_ORDER);
                        statement.setString(1, users[i]);
                        statement.executeUpdate();
                        statement = connection.prepareStatement(SQL_DELETE_USER_DIET_ORDER);
                        statement.setString(1, users[i]);
                        statement.executeUpdate();
                        statement = connection.prepareStatement(SQL_DELETE_USER_PERSONAL_TRAINER_ORDER);
                        statement.setString(1, users[i]);
                        statement.executeUpdate();
                    } else {
                        resultSet.updateDouble(ColumnName.NUMBER_OF_WORKOUTS, numberOfWorkout - 1);
                        resultSet.updateRow();
                    }
                }
            }
            connection.commit();
            return true;
        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
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
    public boolean createPaidOrder(List<Integer> cardList, String login, int numberOfVisits, double price, boolean personalTrainer) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        double cash = 0;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(SQL_SELECT_CARD,
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            statement.setInt(1, cardList.get(0));
            statement.setInt(2, cardList.get(1));
            statement.setInt(3, cardList.get(2));
            statement.setInt(4, cardList.get(3));
            statement.setInt(5, cardList.get(4));
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                cash = resultSet.getDouble(ColumnName.CASH);
                if (cash < price) {
                    return false;
                }
                resultSet.updateDouble(ColumnName.CASH, (cash - price));
                resultSet.updateRow();
            } else {
                return false;
            }
            statement = connection.prepareStatement(SQL_SELECT_USER_BANK,
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                double currentBank = resultSet.getDouble(3);
                resultSet.updateDouble(ColumnName.USER_BANK, (currentBank + price));
                resultSet.updateRow();
            } else {
                statement = connection.prepareStatement(SQL_INSERT_USER_BANK);
                statement.setString(1, login);
                statement.setDouble(2, price);
                statement.executeUpdate();
            }
            statement = connection.prepareStatement(SQL_INSERT_TRAINING);
            statement.setString(1, login);
            statement.setInt(2, numberOfVisits);
            statement.setBoolean(3, personalTrainer);
            statement.executeUpdate();
            connection.commit();
            return true;
        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
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
    public boolean isUserPaid(String login) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_IS_USER_PAID);
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        } finally {
            close(resultSet);
            close(statement);
            close(connection);
        }
    }

    @Override
    public List<Double> calculateOrder(String login, int numberOfVisits, boolean personalTrainer) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Double> list = new ArrayList<>();
        double discount = 0;
        double trainingPrice = 0;
        double personalTrainerPrice = 0;
        double sum = 0;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_SELECT_PRICE);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                trainingPrice = resultSet.getDouble(ColumnName.TRAINING_PRICE);
                if (personalTrainer) {
                    personalTrainerPrice = resultSet.getDouble(ColumnName.PRICE_WITH_TRAINER);
                }
                sum = ((trainingPrice + personalTrainerPrice) * numberOfVisits);
                statement = connection.prepareStatement(SQL_SELECT_USER_DISCOUNT);
                statement.setString(1, login);
                statement.setDouble(2, sum);
                resultSet = statement.executeQuery();
                if (resultSet.last()) {
                    discount = resultSet.getDouble(ColumnName.DISCOUNTER);
                }
                sum *= (1 - discount / 100);
            }
            list.add(sum);
            list.add(discount);
            return list;
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        } finally {
            close(resultSet);
            close(statement);
            close(connection);
        }
    }

    @Override
    public List<User> findAllTrainersUsers(String trainerLogin) throws DaoException {
        List<User> users = new ArrayList<User>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_SELECT_ALL_TRAINERS_USERS);
            statement.setString(1, trainerLogin);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setLogin(resultSet.getString(ColumnName.USER_LOGIN));
                users.add(user);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        } finally {
            close(resultSet);
            close(statement);
            close(connection);
        }
        return users;
    }

    @Override
    public boolean becomePersonalTrainer(String trainerLogin, String userLogin) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(SQL_PERSONAL_TRAINER_ORDER);
            statement.setString(1, trainerLogin);
            statement.setString(2, userLogin);
            statement.executeUpdate();
            statement = connection.prepareStatement(SQL_SELECT_PAID_USERS,
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            statement.setString(1, userLogin);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                resultSet.updateBoolean(ColumnName.ASSIGNED_TRAINER, true);
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
    public List<User> findAllUsers() throws DaoException {
        List<User> users = new ArrayList<User>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_SELECT_ALL_USERS);
            while (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getInt(ColumnName.USER_ID));
                user.setLogin(resultSet.getString(ColumnName.LOGIN));
                user.setFirstName(resultSet.getString(ColumnName.FIRST_NAME));
                user.setLastName(resultSet.getString(ColumnName.LAST_NAME));
                user.setLogin(resultSet.getString(ColumnName.LOGIN));
                user.setEmail(resultSet.getString(ColumnName.EMAIL));
                users.add(user);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        } finally {
            close(resultSet);
            close(statement);
            close(connection);
        }
        return users;
    }

    @Override
    public List<User> findAllPaidUsers() throws DaoException {
        List<User> users = new ArrayList<User>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_SELECT_ALL_PAID_USERS);
            while (resultSet.next()) {
                User user = new User();
                user.setFirstName(resultSet.getString(ColumnName.FIRST_NAME));
                user.setLastName(resultSet.getString(ColumnName.LAST_NAME));
                user.setLogin(resultSet.getString(ColumnName.LOGIN));
                users.add(user);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        } finally {
            close(resultSet);
            close(statement);
            close(connection);
        }
        return users;
    }

    @Override
    public List<User> findAllPaidUsersWithPersonalTrainer() throws DaoException {
        List<User> users = new ArrayList<User>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_SELECT_ALL_PAID_USERS_WITH_PERSONAL_TRAINER);

            while (resultSet.next()) {
                User user = new User();
                user.setFirstName(resultSet.getString(ColumnName.FIRST_NAME));
                user.setLastName(resultSet.getString(ColumnName.LAST_NAME));
                user.setLogin(resultSet.getString(ColumnName.LOGIN));
                users.add(user);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        } finally {
            close(resultSet);
            close(statement);
            close(connection);
        }
        return users;
    }

    @Override
    public List<User> findAllUsersWithoutDiet() throws DaoException {
        List<User> users = new ArrayList<User>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_SELECT_ALL_USERS_WITHOUT_DIET);
            while (resultSet.next()) {
                User user = new User();
                user.setFirstName(resultSet.getString(ColumnName.FIRST_NAME));
                user.setLastName(resultSet.getString(ColumnName.LAST_NAME));
                user.setLogin(resultSet.getString(ColumnName.LOGIN));
                users.add(user);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        } finally {
            close(resultSet);
            close(statement);
            close(connection);
        }
        return users;
    }

    @Override
    public List<User> findAllUsersWithoutExercises() throws DaoException {
        List<User> users = new ArrayList<User>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_SELECT_ALL_USERS_WITHOUT_EXERCISES);

            while (resultSet.next()) {
                User user = new User();
                user.setFirstName(resultSet.getString(ColumnName.FIRST_NAME));
                user.setLastName(resultSet.getString(ColumnName.LAST_NAME));
                user.setLogin(resultSet.getString(ColumnName.LOGIN));
                users.add(user);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        } finally {
            close(resultSet);
            close(statement);
            close(connection);
        }
        return users;
    }

    @Override
    public List<User> findAllDeletedUsers() throws DaoException {
        List<User> users = new ArrayList<User>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_SELECT_ALL_DELETED_USERS);

            while (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getInt(ColumnName.USER_ID));
                user.setFirstName(resultSet.getString(ColumnName.FIRST_NAME));
                user.setLastName(resultSet.getString(ColumnName.LAST_NAME));
                user.setLogin(resultSet.getString(ColumnName.LOGIN));
                user.setEmail(resultSet.getString(ColumnName.EMAIL));
//                user.setRole(resultSet.getInt(ColumnName.ROLE));
                users.add(user);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        } finally {
            close(resultSet);
            close(statement);
            close(connection);
        }
        return users;
    }

    @Override
    public List<User> findAllTrainers() throws DaoException {
        List<User> users = new ArrayList<User>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_SELECT_ALL_TRAINERS);
            while (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getInt(ColumnName.USER_ID));
                user.setFirstName(resultSet.getString(ColumnName.FIRST_NAME));
                user.setLastName(resultSet.getString(ColumnName.LAST_NAME));
                user.setLogin(resultSet.getString(ColumnName.LOGIN));
                user.setEmail(resultSet.getString(ColumnName.EMAIL));
                users.add(user);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        } finally {
            close(resultSet);
            close(statement);
            close(connection);
        }
        return users;
    }

    @Override
    public boolean createUser(User user) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_CREATE_USER);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getLogin());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getEmail());
            statement.setInt(6, ColumnName.BASE_ROLE);
            statement.setBoolean(7, ColumnName.BASE_STATUS);
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
    public boolean isUserExist(String login) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean isUserExists = false;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_EXIST_USER_LOGIN);
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                isUserExists = true;
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        } finally {
            close(resultSet);
            close(statement);
            close(connection);
        }
        return isUserExists;
    }

    @Override
    public User CheckLoginAndPassword(String login, String password) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.prepareStatement(SQL_CORRECT_PASSWORD_AND_LOGIN);
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String realLogin = resultSet.getString(ColumnName.LOGIN);
                String realPassword = resultSet.getString(ColumnName.PASSWORD);
                if (login.equals(realLogin) && password.equals(realPassword)) {
                    user = new User();
                    user.setUserId(resultSet.getInt(ColumnName.USER_ID));
                    user.setLogin(resultSet.getString(ColumnName.LOGIN));
                    user.setRole(resultSet.getString(ColumnName.ROLE));
                    user.setStatus(resultSet.getBoolean(ColumnName.STATUS));
                    resultSet.getBoolean(ColumnName.STATUS);
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        } finally {
            close(resultSet);
            close(statement);
            close(connection);
        }
        return user;
    }
}