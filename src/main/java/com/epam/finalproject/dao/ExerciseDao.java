package com.epam.finalproject.dao;

import com.epam.finalproject.entity.Exercise;
import com.epam.finalproject.exception.DaoException;
import java.util.List;

public interface ExerciseDao extends Dao <Integer, Exercise> {

    List<Exercise> findAllExercises() throws DaoException;
    List<Exercise> findNotSame(String[] currentExercises) throws DaoException;
    List<Exercise> findUserExercises(String login) throws DaoException;
    boolean create(Exercise exercise) throws DaoException;
    boolean createOrder(String userLogin, String[] exercises) throws DaoException;
    boolean deleteChosenExercises(String userLogin, String[] exercises) throws DaoException;
    boolean deleteUserOrder(String userLogin) throws DaoException;
    boolean updateExerciseOrder(String userLogin, String[] exercises) throws DaoException;
    boolean deleteExercise(String[] exercises) throws DaoException;
}