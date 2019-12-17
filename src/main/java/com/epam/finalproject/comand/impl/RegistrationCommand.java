package com.epam.finalproject.comand.impl;

import com.epam.finalproject.comand.constant.ParameterName;
import com.epam.finalproject.dao.impl.UserDaoImpl;
import com.epam.finalproject.entity.User;
import com.epam.finalproject.comand.constant.AttributeName;
import com.epam.finalproject.comand.constant.MessageName;
import com.epam.finalproject.comand.constant.PathPage;
import com.epam.finalproject.exception.CommandException;
import com.epam.finalproject.exception.DaoException;
import com.epam.finalproject.manager.ConfigurationManager;
import com.epam.finalproject.manager.MessageManager;
import com.epam.finalproject.service.UserService;
import com.epam.finalproject.util.InputInfoValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RegistrationCommand implements ActionCommand {

    private static Logger logger = LogManager.getLogger();
    private UserService userService = new UserService();
    private final static String PARAM_LOGIN = "login";
    private final static String PARAM_PASSWORD = "password";
    private final static String PARAM_REPEAT_PASSWORD = "repeat password";
    private final static String PARAM_FIRST_NAME = "first name";
    private final static String PARAM_LAST_NAME = "last name";
    private final static String PARAM_MAIL = "mail";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        UserDaoImpl userDao = new UserDaoImpl();
        boolean isParamValid = true;
        String page = null;

        String login = request.getParameter(PARAM_LOGIN);
        String password =  request.getParameter(PARAM_PASSWORD);
        String repeatPassword = request.getParameter(PARAM_REPEAT_PASSWORD);
        String firstName = request.getParameter(PARAM_FIRST_NAME);
        String lastName = request.getParameter(PARAM_LAST_NAME);
        String mail = request.getParameter(PARAM_MAIL);

        try {
            if (userDao.isUserExist(login)) {
                request.setAttribute(AttributeName.ERROR_LOGIN_ALREADY_EXISTS, MessageManager.getProperty(MessageName.LOGIN_ALREADY_EXISTS));
                isParamValid = false;
            } else {
                if (InputInfoValidator.isLoginOrPasswordCorrect(login)) {
                    request.setAttribute(AttributeName.LOGIN, login);
                } else {
                    request.setAttribute(AttributeName.ERROR_LOGIN, MessageManager.getProperty(MessageName.INCORRECT_LOGIN));
                    isParamValid = false;
                }
            }
        } catch (DaoException e) {
            logger.error(e);
            throw new CommandException(e);
        }

        if (!InputInfoValidator.isLoginOrPasswordCorrect(password)) {
            request.setAttribute(AttributeName.ERROR_PASSWORD, MessageManager.getProperty(MessageName.INCORRECT_PASSWORD));
            isParamValid = false;
        }

        if (!InputInfoValidator.isPasswordsMatch(password, repeatPassword)) {
            request.setAttribute(AttributeName.ERROR_REPEAT_PASSWORD, MessageManager.getProperty(MessageName.INCORRECT_REPEAT_PASSWORD));
            isParamValid = false;
        }

        if (InputInfoValidator.iskNameCorrect(firstName)) {
            request.setAttribute(AttributeName.FIRST_NAME, firstName);
        } else {
            request.setAttribute(AttributeName.ERROR_FIRST_NAME, MessageManager.getProperty(MessageName.INCORRECT_FIRST_NAME));
            isParamValid = false;
        }

        if (InputInfoValidator.iskNameCorrect(lastName)) {
            request.setAttribute(AttributeName.LAST_NAME, lastName);
        } else {
            request.setAttribute(AttributeName.ERROR_LAST_NAME, MessageManager.getProperty(MessageName.INCORRECT_LAST_NAME));
            isParamValid = false;
        }

        if (InputInfoValidator.isEmailCorrect(mail)) {
            request.setAttribute(AttributeName.MAIL, mail);
        } else {
            request.setAttribute(AttributeName.ERROR_MAIL, MessageManager.getProperty(MessageName.INCORRECT_EMAIL));
            isParamValid = false;
        }

        if (isParamValid) {
            try {
                userDao.createUser(new User(firstName, lastName, login, password, mail));
                request.setAttribute(AttributeName.REDIRECT, ParameterName.REDIRECT);
            } catch (DaoException e) {
                e.printStackTrace();
            }
            page = ConfigurationManager.getProperty(PathPage.REDIRECT_LOGIN_BUTTON);
            return page;
        }

        page = ConfigurationManager.getProperty(PathPage.REGISTRATION_PAGE);
        return page;
    }
}