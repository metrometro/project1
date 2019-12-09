package com.epam.finalproject.service;

import com.epam.finalproject.dao.DietDao;
import com.epam.finalproject.dao.ExerciseDao;
import com.epam.finalproject.entity.Diet;
import com.epam.finalproject.exception.DaoException;
import com.epam.finalproject.exception.ServiceException;
import com.epam.finalproject.factory.DaoFactory;
import com.epam.finalproject.util.XssSecurity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class DietService {

    private static Logger logger = LogManager.getLogger();

    public boolean deleteDiets(String[] diets) throws ServiceException {
        DietDao dietDaoImpl = DaoFactory.getInstance().getDietDao();
        if (diets == null) {
            return false;
        }

        try {
            dietDaoImpl.deleteDiets(diets);
            return true;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    public boolean createDiet(String dietType) throws ServiceException {
        DietDao dietDaoImpl = DaoFactory.getInstance().getDietDao();
        Diet diet = new Diet();
        dietType = XssSecurity.protectFromXssAttack(dietType);
        diet.setDietType(dietType);

        try {
            dietDaoImpl.create(diet);
            return true;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }


    public boolean deleteChosenDiet(String userLogin, String[] diet) throws ServiceException {

        DietDao dietDaoImpl = DaoFactory.getInstance().getDietDao();
        try {
            dietDaoImpl.deleteChosenDiet(userLogin, diet);
            return true;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    public boolean setDiet(String userLogin, String dietType) throws ServiceException {
        DietDao dietDaoImpl = DaoFactory.getInstance().getDietDao();
        try {
            dietDaoImpl.createOrder(userLogin, dietType);
            return true;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    public boolean updateOrder(String userLogin, String dietType) throws ServiceException {
        DietDao dietDaoImpl = DaoFactory.getInstance().getDietDao();
        try {
            dietDaoImpl.updateDietOrder(userLogin, dietType);
            return true;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    public List<Diet> findUserDiet(String userLogin) throws ServiceException {
        DietDao dietDaoImpl = DaoFactory.getInstance().getDietDao();
        List<Diet> diets = null;
        try {
            diets = dietDaoImpl.findUserDiet(userLogin);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return diets;
    }

    public List<Diet> findAllDiets() throws ServiceException {
        DietDao dietDaoImpl = DaoFactory.getInstance().getDietDao();
        List<Diet> diets = null;
        try {
            diets = dietDaoImpl.findAll();
            return diets;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }
}