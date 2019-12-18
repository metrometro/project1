package com.epam.finalproject.comand.impl;

import com.epam.finalproject.comand.AttributeName;
import com.epam.finalproject.comand.ParameterName;
import com.epam.finalproject.comand.PathPage;
import com.epam.finalproject.exception.CommandException;
import com.epam.finalproject.exception.ServiceException;
import com.epam.finalproject.manager.ConfigurationManager;
import com.epam.finalproject.service.DietService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class TrainerSetPersonalDietCommand implements ActionCommand {

    private static Logger logger = LogManager.getLogger();
    private DietService dietService = new DietService();
    private final static String PARAM_DIET = "diet";
    private final static String PARAM_USER_LOGIN = "userLogin";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        StringBuilder builder = new StringBuilder();
        String page = null;
        String dietType = request.getParameter(PARAM_DIET);
        String userLogin = request.getParameter(PARAM_USER_LOGIN);
        try {
            dietService.setDiet(userLogin, dietType);
        } catch (ServiceException e) {
            logger.error(e);
            throw new CommandException(e);
        }

        HttpSession session = request.getSession();
        session.setAttribute(AttributeName.USER_LOGIN, userLogin);
        request.setAttribute(AttributeName.REDIRECT, ParameterName.REDIRECT);
        page = ConfigurationManager.getProperty(PathPage.REDIRECT_SHOW_USER_DIET);
        return page;
    }
}