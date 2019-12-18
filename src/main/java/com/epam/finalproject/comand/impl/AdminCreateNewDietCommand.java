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

public class AdminCreateNewDietCommand implements ActionCommand {

    private static Logger logger = LogManager.getLogger();
    private DietService dietService = new DietService();
    private final static String PARAM_DIET = "diet";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String exercise = request.getParameter(PARAM_DIET);
        String page = null;
        try {
            dietService.createDiet(exercise);
        } catch (ServiceException e) {
            logger.error(e);
            throw new CommandException(e);
        }
        request.setAttribute(AttributeName.REDIRECT, ParameterName.REDIRECT);
        page = ConfigurationManager.getProperty(PathPage.REDIRECT_SHOW_ALL_DIETS);
        return page;
    }
}