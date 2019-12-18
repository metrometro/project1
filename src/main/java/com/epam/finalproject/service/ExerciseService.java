package com.epam.finalproject.service;

import com.epam.finalproject.dao.ExerciseDao;
import com.epam.finalproject.entity.Exercise;
import com.epam.finalproject.exception.DaoException;
import com.epam.finalproject.exception.ServiceException;
import com.epam.finalproject.factory.DaoFactory;
import com.epam.finalproject.util.XssSecurity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

public class ExerciseService {

    private static Logger logger = LogManager.getLogger();

    /**
     * Method for creating execise
     * @param exerciseType - exercises
     * @return boolean type
     * @exception ServiceException
     */

    public boolean createExercise(String exerciseType) throws ServiceException {
        ExerciseDao exerciseDao = DaoFactory.getInstance().getExerciseDao();
        if (exerciseType == null || exerciseType.isEmpty()) {
            return false;
        }
        Exercise exercise = new Exercise();
        exerciseType = XssSecurity.protectFromXssAttack(exerciseType);
        exercise.setExerciseType(exerciseType);
        try {
            exerciseDao.create(exercise);
            return true;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    /**
     * Method for deleting exercise
     * @param exercises - exercises array
     * @return boolean type
     * @exception ServiceException
     */

    public boolean deleteExercises(String[] exercises) throws ServiceException {
        ExerciseDao exerciseDao = DaoFactory.getInstance().getExerciseDao();
        if (exercises == null) {
            return false;
        }

        try {
            exerciseDao.deleteExercise(exercises);
            return true;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    /**
     * Method for updating chosen exercises
     * @param userLogin - user login
     * @param exercises - exercises array
     * @return boolean type
     * @exception ServiceException
     */

    public boolean updateExercisesOrder(String userLogin, String[] exercises) throws ServiceException {
        ExerciseDao exerciseDao = DaoFactory.getInstance().getExerciseDao();
        if (exercises == null || userLogin == null || userLogin.isEmpty()) {
            return false;
        }
        try {
            exerciseDao.updateExerciseOrder(userLogin, exercises);
            return true;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    /**
     * Method for deleting chosen exercises
     * @param userLogin - user login
     * @param exercises - exercises array
     * @return boolean type
     * @exception ServiceException
     */

    public boolean deleteChosenExercises(String userLogin, String[] exercises) throws ServiceException {
        ExerciseDao exerciseDao = DaoFactory.getInstance().getExerciseDao();
        if (exercises == null || userLogin == null || userLogin.isEmpty() ) {
            return false;
        }
        try {
            exerciseDao.deleteChosenExercises(userLogin, exercises);
            return true;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    /**
     * Method for deleting user exercise order
     * @param userLogin - user login
     * @return boolean type
     * @exception ServiceException
     */

    public boolean deleteUserExerciseOrder(String userLogin) throws ServiceException {
        ExerciseDao exerciseDao = DaoFactory.getInstance().getExerciseDao();
        if (userLogin == null || userLogin.isEmpty()) {
            return false;
        }
        try {
            exerciseDao.deleteUserOrder(userLogin);
            return true;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    /**
     * Method for setting exercises
     * @param userLogin - user login
     * @param exercises - exercises array
     * @return boolean type
     * @exception ServiceException
     */

    public boolean setExercises(String userLogin, String[] exercises) throws ServiceException {
        ExerciseDao exerciseDao = DaoFactory.getInstance().getExerciseDao();
        if (exercises == null || userLogin == null || userLogin.isEmpty()) {
            return false;
        }
        try {
            exerciseDao.createOrder(userLogin, exercises);
            return true;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    /**
     * Method for finding all user exercises
     * @param userLogin - user login
     * @return exercises
     * @exception ServiceException
     */

    public List<Exercise> findUserExercises(String userLogin) throws ServiceException {
        ExerciseDao exerciseDao = DaoFactory.getInstance().getExerciseDao();
        List<Exercise> exercises = null;
        try {
            exercises = exerciseDao.findUserExercises(userLogin);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return exercises;
    }

    /**
     * Method for finding all not same exercises
     * @param currentExercises - current exercises
     * @return exercises
     * @exception ServiceException
     */

    public List<Exercise> findAllNotSameExercises(String[] currentExercises) throws ServiceException {
        ExerciseDao exerciseDao = DaoFactory.getInstance().getExerciseDao();
        List<Exercise> exercises = null;
        try {
            exercises = exerciseDao.findNotSame(currentExercises);
            return exercises;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    /**
     * Method for finding all exercises
     * @return exercises
     * @exception ServiceException
     */

    public List<Exercise> findAllExercises() throws ServiceException {
        ExerciseDao exerciseDao = DaoFactory.getInstance().getExerciseDao();
        List<Exercise> exercises = null;
        try {
            exercises = exerciseDao.findAllExercises();
            return exercises;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }
}