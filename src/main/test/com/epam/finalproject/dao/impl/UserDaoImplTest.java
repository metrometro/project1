package com.epam.finalproject.dao.impl;

import com.epam.finalproject.dao.UserDao;
import com.epam.finalproject.exception.DaoException;
import com.epam.finalproject.factory.DaoFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class UserDaoImplTest {

    private UserDao userDao;

    @BeforeClass
    public void setUp() {
        userDao = DaoFactory.getInstance().getUserDao();
    }

    @Test
    public void testIsUserExist() throws DaoException {
        String login = "admin";
        boolean actual = userDao.isUserExist(login);
        boolean expected = true;

        assertEquals(actual, expected);
    }

    @AfterClass
    public void tierDown() {
        userDao = null;
    }
}