package com.epam.finalproject.dao;

import com.epam.finalproject.entity.User;
import com.epam.finalproject.exception.DaoException;
import java.util.List;

public interface UserDao extends Dao <Integer, User>{

    User CheckLoginAndPassword(String login, String password) throws DaoException;
    List<User> findAllUsers() throws DaoException;
    List<User> findAllPaidUsers() throws DaoException;
    List<User> findAllUsersWithoutDiet() throws DaoException;
    List<User> findAllUsersWithoutExercises() throws DaoException;
    List<User> findAllDeletedUsers() throws DaoException;
    List<User> findAllTrainers() throws DaoException;
    List<User> findAllTrainersUsers(String trainerLogin) throws DaoException;
    List<User> findAllPaidUsersWithPersonalTrainer() throws DaoException;
    List<Double> calculateOrder(String login, int numberOfVisits, boolean personalTrainer) throws DaoException;
    List<String> findCurrentUserComments(String userLogin) throws DaoException;
    List<String> findUserStatus(String login) throws DaoException;
    boolean makeUser(String userLogin) throws DaoException;
    boolean makeTRainer(String userLogin) throws DaoException;
    boolean restoreUser(String userLogin) throws DaoException;
    boolean createComment(String userLogin, String comment) throws DaoException;
    boolean deleteUser(String userLogin) throws DaoException;
    boolean createUser(User user) throws DaoException;
    boolean isUserExist(String login) throws DaoException;
    boolean becomePersonalTrainer(String trainerLogin, String userLogin) throws DaoException;
    boolean isUserPaid(String login) throws DaoException;
    boolean markUsersVisit(String[] users) throws DaoException;
    boolean createPaidOrder(List<Integer> cardList, String login, int numberOfVisits, double price, boolean personalTrainer)
            throws DaoException;

}
