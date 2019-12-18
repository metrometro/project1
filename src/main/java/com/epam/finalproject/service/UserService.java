package com.epam.finalproject.service;

import com.epam.finalproject.comand.AttributeName;
import com.epam.finalproject.comand.MessageName;
import com.epam.finalproject.dao.UserDao;
import com.epam.finalproject.dao.impl.UserDaoImpl;
import com.epam.finalproject.entity.User;
import com.epam.finalproject.exception.DaoException;
import com.epam.finalproject.exception.ServiceException;
import com.epam.finalproject.factory.DaoFactory;
import com.epam.finalproject.manager.MessageManager;
import com.epam.finalproject.util.InputInfoValidator;
import com.epam.finalproject.util.PasswordEncoder;
import com.epam.finalproject.util.XssSecurity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UserService {

    private static Logger logger = LogManager.getLogger();

    /**
     * Method of registration login and creating user
     * @param list - parameters list
     * @param attributes - attributes name
     * @param isParamValid - attributes
     * @return boolean type
     * @exception ServiceException
     */

    public boolean registration(LinkedList<String> list, List<String> attributesName, List<String> attributes, boolean isParamValid) throws ServiceException {
        UserDao userDao = DaoFactory.getInstance().getUserDao();
        String login = list.getFirst();
        list.removeFirst();

        try {
            if (userDao.isUserExist(login)) {
                attributesName.add(AttributeName.ERROR_LOGIN_ALREADY_EXISTS);
                attributes.add(MessageManager.getProperty(MessageName.LOGIN_ALREADY_EXISTS));
                isParamValid = false;
            } else {
                if (InputInfoValidator.isLoginOrPasswordCorrect(login)) {
                    attributesName.add(AttributeName.LOGIN);
                    attributes.add(login);
                } else {
                    attributesName.add(AttributeName.ERROR_LOGIN);
                    attributes.add(MessageManager.getProperty(MessageName.INCORRECT_LOGIN));
                    isParamValid = false;
                }
            }
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }

        String password = list.getFirst();
        list.removeFirst();

        if (!InputInfoValidator.isLoginOrPasswordCorrect(password)) {
            attributesName.add(AttributeName.ERROR_PASSWORD);
            attributes.add(MessageManager.getProperty(MessageName.INCORRECT_PASSWORD));
            isParamValid = false;
        }
        String repeatPassword = list.getFirst();
        list.removeFirst();
        if (!InputInfoValidator.isPasswordsMatch(password, repeatPassword)) {
            attributesName.add(AttributeName.ERROR_REPEAT_PASSWORD);
            attributes.add(MessageManager.getProperty(MessageName.INCORRECT_REPEAT_PASSWORD));
            isParamValid = false;
        }
        String firstName = list.getFirst();
        list.removeFirst();

        if (InputInfoValidator.iskNameCorrect(firstName)) {
            attributesName.add(AttributeName.FIRST_NAME);
            attributes.add(firstName);
        } else {
            attributesName.add(AttributeName.ERROR_FIRST_NAME);
            attributes.add(MessageManager.getProperty(MessageName.INCORRECT_FIRST_NAME));
            isParamValid = false;
        }
        String lastName = list.getFirst();
        list.removeFirst();

        if (InputInfoValidator.iskNameCorrect(lastName)) {
            attributesName.add(AttributeName.LAST_NAME);
            attributes.add(lastName);
        } else {
            attributesName.add(AttributeName.ERROR_LAST_NAME);
            attributes.add(MessageManager.getProperty(MessageName.INCORRECT_LAST_NAME));
            isParamValid = false;
        }
        String mail = list.getFirst();
        list.removeFirst();

        if (InputInfoValidator.isEmailCorrect(mail)) {
            attributesName.add(AttributeName.MAIL);
            attributes.add(mail);
        } else {
            attributesName.add(AttributeName.ERROR_MAIL);
            attributes.add(MessageManager.getProperty(MessageName.INCORRECT_EMAIL));
            isParamValid = false;
        }

        if (isParamValid) {
            User user = new User(firstName, lastName, login, password, mail);
            createUser(user);
        }
        return isParamValid;
    }

    /**
     * Method for making user
     * @param userLogin - user login
     * @return boolean type
     * @exception ServiceException
     */

    public boolean makeUser(String userLogin) throws ServiceException {
        UserDao userDao = DaoFactory.getInstance().getUserDao();
        if (userLogin == null || userLogin.isEmpty()) {
            return false;
        }
        try {
            userDao.makeUser(userLogin);
            return true;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    /**
     * Method for making trainer
     * @param userLogin - user login
     * @return boolean type
     * @exception ServiceException
     */

    public boolean makeTrainer(String userLogin) throws ServiceException {
        UserDao userDao = DaoFactory.getInstance().getUserDao();
        if (userLogin == null || userLogin.isEmpty()) {
            return false;
        }
        try {
            userDao.makeTRainer(userLogin);
            return true;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    /**
     * Method for restoring user
     * @param userLogin - user login
     * @return boolean type
     * @exception ServiceException
     */

    public boolean restoreUser(String userLogin) throws ServiceException {
        UserDao userDao = DaoFactory.getInstance().getUserDao();
        if (userLogin == null || userLogin.isEmpty()) {
            return false;
        }
        try {
            userDao.restoreUser(userLogin);
            return true;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    /**
     * Method for finding user state
     * @param userLogin - user login
     * @return user status
     * @exception ServiceException
     */

    public List<String> showCurrentUserStatus(String userLogin) throws ServiceException {
        UserDao userDao = DaoFactory.getInstance().getUserDao();
        List<String> list = new ArrayList<>();
        try {
            list = userDao.findUserStatus(userLogin);
            return list;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    /**
     * Method for finding user reviews
     * @param userLogin - user login
     * @return reviews
     * @exception ServiceException
     */

    public List<String> findCurrentUserComments(String userLogin) throws ServiceException {
        UserDao userDao = DaoFactory.getInstance().getUserDao();
        List<String> comments = new ArrayList<>();
        try {
            comments = userDao.findCurrentUserComments(userLogin);
            return comments;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    /**
     * Method for creating review
     * @param userLogin - user login
     * @param comment - review value
     * @return boolean type
     * @exception ServiceException
     */

    public boolean createComment(String userLogin, String comment) throws ServiceException {
        UserDao userDao = DaoFactory.getInstance().getUserDao();
        if (userLogin == null || userLogin.isEmpty() || comment == null || comment.isEmpty()) {
            return false;
        }
        comment = XssSecurity.protectFromXssAttack(comment);
        comment = comment.substring(0, 10);

        System.out.println(comment);
        try {
            userDao.createComment(userLogin, comment);
            return true;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    /**
     * Method for marking chosen users
     * @param users - list of users
     * @return boolean type
     * @exception ServiceException
     */

    public boolean markUsersVisit(String[] users) throws ServiceException {
        UserDao userDao = DaoFactory.getInstance().getUserDao();
        if (users == null) {
            return false;
        }
        if (users == null) {
            return false;
        }
        try {
            userDao.markUsersVisit(users);
            return true;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    /**
     * Method for checking is user paid
     * @param userLogin - user login
     * @return boolean type
     * @exception ServiceException
     */

    public boolean isUserPaid(String userLogin) throws ServiceException {
        UserDao userDao = DaoFactory.getInstance().getUserDao();
        if (userLogin == null || userLogin.isEmpty()) {
            return false;
        }
        try {
            return userDao.isUserPaid(userLogin);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    /**
     * Method for creating orders
     * @return boolean type
     * @param list - orders list
     * @exception ServiceException
     */

    public boolean createOrder(List<String> list) throws ServiceException {
        UserDao userDao = DaoFactory.getInstance().getUserDao();
        if (list == null) {
            return false;
        }
        List<Integer> cardList = new ArrayList<>();
        boolean isOrderCreate = false;
        int code = 0;
        for (int i = 0; i < 5; i++) {
            code = Integer.parseInt(list.get(i));
            cardList.add(code);
        }
        String login = list.get(5);
        int numberOfVisits = Integer.parseInt(list.get(6));
        double price = Double.parseDouble(list.get(7));
        boolean personalTrainer = new Boolean(list.get(8));
        try {
            isOrderCreate = userDao.createPaidOrder(cardList, login, numberOfVisits, price, personalTrainer);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return isOrderCreate;
    }

    /**
     * Method for order calculating
     * @param login - user login
     * @param numberOfVisits - number of visits
     * @param personalTrainer - is personal trainer
     * @return list Double
     * @exception ServiceException
     */

    public List<Double> calculateOrder(String login, int numberOfVisits, boolean personalTrainer) throws ServiceException {
        UserDao userDao = DaoFactory.getInstance().getUserDao();
        try {
            List<Double> list = userDao.calculateOrder(login, numberOfVisits, personalTrainer);
            return list;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    /**
     * Method for checking card
     * @return boolean type
     * @param list - card values
     * @exception ServiceException
     */

    public boolean checkCard(List<String> list) {
        if (list == null) {
            return false;
        }
        boolean isCardValid = true;
        for (int i = 0; i < 4; i++) {
            if (!InputInfoValidator.isCardCodeValid(list.get(i))) {
                isCardValid = false;
                return isCardValid;
            }
        }
        if (!InputInfoValidator.isCardCvcValid(list.get(4))) {
            isCardValid = false;
        }
        return isCardValid;
    }

    /**
     * Method for finding all trainer's users
     * @return users
     * @param trainerLogin - trainer login
     * @exception ServiceException
     */

    public List<User> findAllTrainersUsers(String trainerLogin) throws ServiceException {
        UserDao userDao = DaoFactory.getInstance().getUserDao();
        List<User> users = null;
        try {
            users = userDao.findAllTrainersUsers(trainerLogin);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return users;
    }

    /**
     * Method for deleting user
     * @return booleantype
     * @param userLogin - user login
     * @exception ServiceException
     */

    public boolean deleteUser(String userLogin) throws ServiceException {
        UserDao userDao = DaoFactory.getInstance().getUserDao();
        if (userLogin == null || userLogin.isEmpty()) {
            return false;
        }
        try {
            userDao.deleteUser(userLogin);
            return true;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    /**
     * Method to become a personal trainer
     * @return boolean type
     * @param trainerLogin - trainer login
     * @param userLogin - user login
     * @exception ServiceException
     */

    public boolean becomePersonalTrainer(String trainerLogin, String userLogin) throws ServiceException {
        UserDao userDao = DaoFactory.getInstance().getUserDao();
        if (userLogin == null || userLogin.isEmpty() || trainerLogin == null || trainerLogin.isEmpty()) {
            return false;
        }
        try {
            userDao.becomePersonalTrainer(trainerLogin, userLogin);
            return true;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    /**
     * Method for finding all paid users
     * @return users
     * @exception ServiceException
     */

    public List<User> findAllPaidUsers() throws ServiceException {
        UserDao userDao = DaoFactory.getInstance().getUserDao();
        List<User> users = null;
        try {
            users = userDao.findAllPaidUsers();
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return users;
    }

    /**
     * Method for finding all users with personal trainer
     * @return users
     * @exception ServiceException
     */

    public List<User> findAllPaidUsersWithPersonalTrainer() throws ServiceException {
        UserDao userDao = DaoFactory.getInstance().getUserDao();
        List<User> users = null;
        try {
            users = userDao.findAllPaidUsersWithPersonalTrainer();
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return users;
    }

    /**
     * Method for finding all users without exercises
     * @return users
     * @exception ServiceException
     */

    public List<User> findAllUsersWithoutExercises() throws ServiceException {
        UserDao userDao = DaoFactory.getInstance().getUserDao();
        List<User> users = null;
        try {
            users = userDao.findAllUsersWithoutExercises();
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return users;
    }

    /**
     * Method for finding all users without diet
     * @return users
     * @exception ServiceException
     */

    public List<User> findAllUsersWithoutDiet() throws ServiceException {
        UserDao userDao = DaoFactory.getInstance().getUserDao();
        List<User> users = null;
        try {
            users = userDao.findAllUsersWithoutDiet();
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return users;
    }

    /**
     * Method for finding all userstrainers
     * @return users
     * @exception ServiceException
     */

    public List<User> findAllTrainers() throws ServiceException {
        UserDao userDao = DaoFactory.getInstance().getUserDao();
        List<User> trainers = null;
        try {
            trainers = userDao.findAllTrainers();
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return trainers;
    }

    /**
     * Method for finding all users
     * @return users
     * @exception ServiceException
     */

    public List<User> findAllUsers() throws ServiceException {
        UserDao userDao = DaoFactory.getInstance().getUserDao();
        List<User> users = null;
        try {
            users = userDao.findAllUsers();
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return users;
    }

    /**
     * Method for checking user login and password
     * @param login - user login
     * @param pass - user password
     * @return user
     * @exception ServiceException
     */

    public User checkLoginAndPass(String login, String pass) throws ServiceException {
        UserDao userDao = new UserDaoImpl();
        pass = PasswordEncoder.encode(pass);
        User user = null;
        try {
            user = userDao.CheckLoginAndPassword(login, pass);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return user;
    }

    /**
     * Method for finding all deleted users
     * @return users
     * @exception ServiceException
     */

    public List<User> findAllDeletedUsers() throws ServiceException {
        UserDao userDao = DaoFactory.getInstance().getUserDao();
        List<User> users = null;
        try {
            users = userDao.findAllDeletedUsers();
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return users;
    }

    /**
     * Method for creating new user
     * @param user - user object
     * @return boolean type
     * @exception ServiceException
     */

    private boolean createUser(User user) throws ServiceException {
        UserDao userDao = DaoFactory.getInstance().getUserDao();
        String pass = PasswordEncoder.encode(user.getPassword());
        user.setPassword(pass);

        try {
            userDao.createUser(user);
            return true;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }

    }
}