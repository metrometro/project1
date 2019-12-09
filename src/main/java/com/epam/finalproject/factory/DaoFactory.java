package com.epam.finalproject.factory;

import com.epam.finalproject.dao.impl.*;

public class DaoFactory {

    private static DaoFactory instance;
    private DietDaoImpl dietDao = new DietDaoImpl();
    private ExerciseDaoImpl exerciseDao = new ExerciseDaoImpl();
    private UserDaoImpl userDao = new UserDaoImpl();

    private DaoFactory() {};

    public DietDaoImpl getDietDao() {
        return dietDao;
    }

    public ExerciseDaoImpl getExerciseDao() {
        return exerciseDao;
    }

    public UserDaoImpl getUserDao() {
        return userDao;
    }

    public static DaoFactory getInstance() {
        if (instance == null) {
            instance = new DaoFactory();
        }
        return instance;
    }
}