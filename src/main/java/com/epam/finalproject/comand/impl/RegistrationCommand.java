package com.epam.finalproject.comand.impl;

import com.epam.finalproject.comand.ParameterName;
import com.epam.finalproject.dao.impl.UserDaoImpl;
import com.epam.finalproject.comand.AttributeName;
import com.epam.finalproject.comand.PathPage;
import com.epam.finalproject.exception.CommandException;
import com.epam.finalproject.exception.ServiceException;
import com.epam.finalproject.manager.ConfigurationManager;
import com.epam.finalproject.service.UserService;
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
        List<String> attributesName = new ArrayList<>();
        List<String> attributes = new ArrayList<>();
        LinkedList<String> list = new LinkedList<>();
        boolean isParamValid = true;
        String page = null;
        String login = request.getParameter(PARAM_LOGIN);
        String password =  request.getParameter(PARAM_PASSWORD);
        String repeatPassword = request.getParameter(PARAM_REPEAT_PASSWORD);
        String firstName = request.getParameter(PARAM_FIRST_NAME);
        String lastName = request.getParameter(PARAM_LAST_NAME);
        String mail = request.getParameter(PARAM_MAIL);

        list.add(login);
        list.add(password);
        list.add(repeatPassword);
        list.add(firstName);
        list.add(lastName);
        list.add(mail);

        try {
            isParamValid = userService.registration(list, attributesName, attributes, isParamValid);
        } catch (ServiceException e) {
            logger.error(e);
            throw new CommandException(e);
        }
        for (int i = 0; i < attributesName.size(); i++) {
            request.setAttribute(attributesName.get(i), attributes.get(i));
        }
        if (isParamValid) {
            request.setAttribute(AttributeName.REDIRECT, ParameterName.REDIRECT);
            page = ConfigurationManager.getProperty(PathPage.REDIRECT_LOGIN_BUTTON);
            return page;
        }
        page = ConfigurationManager.getProperty(PathPage.REGISTRATION_PAGE);
        return page;
    }
}