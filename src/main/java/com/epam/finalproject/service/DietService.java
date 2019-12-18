package com.epam.finalproject.service;

import com.epam.finalproject.dao.DietDao;
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

    /**
     * Method for deleting  diet
     * @param diets - diet array
     * @return boolean type
     * @exception ServiceException
     */

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

    /**
     * Method for creating diet
     * @param dietType - diet
     * @return boolean type
     * @exception ServiceException
     */

    public boolean createDiet(String dietType) throws ServiceException {
        DietDao dietDaoImpl = DaoFactory.getInstance().getDietDao();
        if (dietType == null || dietType.isEmpty()) {
            return false;
        }
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

    /**
     * Method for deleting chosen diet
     * @param userLogin - user login
     * @param diet - diet array
     * @return boolean type
     * @exception ServiceException
     */

    public boolean deleteChosenDiet(String userLogin, String[] diet) throws ServiceException {
        DietDao dietDaoImpl = DaoFactory.getInstance().getDietDao();
        if (diet == null || userLogin == null) {
            return false;
        }
        try {
            dietDaoImpl.deleteChosenDiet(userLogin, diet);
            return true;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    /**
     * Method for setting diet
     * @param userLogin - user login
     * @param dietType - diet
     * @return boolean type
     * @exception ServiceException
     */

    public boolean setDiet(String userLogin, String dietType) throws ServiceException {
        DietDao dietDaoImpl = DaoFactory.getInstance().getDietDao();
        if (dietType == null || userLogin == null || dietType.isEmpty() || userLogin.isEmpty()) {
            return false;
        }
        try {
            dietDaoImpl.createOrder(userLogin, dietType);
            return true;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    /**
     * Method for updating diet
     * @param userLogin - user login
     * @param dietType - diet
     * @return boolean type
     * @exception ServiceException
     */

    public boolean updateOrder(String userLogin, String dietType) throws ServiceException {
        DietDao dietDaoImpl = DaoFactory.getInstance().getDietDao();
        if (dietType == null || userLogin == null || dietType.isEmpty() || userLogin.isEmpty()) {
            return false;
        }
        try {
            dietDaoImpl.updateDietOrder(userLogin, dietType);
            return true;
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    /**
     * Method for finding user diet
     * @param userLogin - user login
     * @return diet
     * @exception ServiceException
     */

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

    /**
     * Method for finding all diets
     * @return diet
     * @exception ServiceException
     */

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