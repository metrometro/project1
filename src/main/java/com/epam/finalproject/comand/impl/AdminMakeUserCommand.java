package com.epam.finalproject.comand.impl;

import com.epam.finalproject.comand.AttributeName;
import com.epam.finalproject.comand.ParameterName;
import com.epam.finalproject.comand.PathPage;
import com.epam.finalproject.exception.CommandException;
import com.epam.finalproject.exception.ServiceException;
import com.epam.finalproject.manager.ConfigurationManager;
import com.epam.finalproject.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;

public class AdminMakeUserCommand implements ActionCommand {

    private static Logger logger = LogManager.getLogger();
    private UserService userService = new UserService();
    private final static String PARAM_USER_LOGIN = "userLogin";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        String userLogin = request.getParameter(PARAM_USER_LOGIN);
        try {
            userService.makeUser(userLogin);
        } catch (ServiceException e) {
            logger.error(e);
            throw new CommandException(e);
        }
        request.setAttribute(AttributeName.REDIRECT, ParameterName.REDIRECT);
        page = ConfigurationManager.getProperty(PathPage.REDIRECT_SHOW_ALL_TRAINERS);
        return page;
    }
}