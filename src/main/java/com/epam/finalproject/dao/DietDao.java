package com.epam.finalproject.dao;

import com.epam.finalproject.entity.Diet;
import com.epam.finalproject.exception.DaoException;
import java.util.List;

public interface DietDao extends Dao <Integer, Diet> {

    List<Diet> findAll() throws DaoException;
    List<Diet> findUserDiet(String userLogin) throws DaoException;
    boolean createOrder(String userLogin, String dietType) throws DaoException;
    boolean deleteChosenDiet(String userLogin, String[] diet) throws DaoException;
    boolean updateDietOrder(String userLogin, String dietType) throws DaoException;
    boolean create(Diet diet) throws DaoException;
    boolean deleteDiets(String[] diets) throws DaoException;
}