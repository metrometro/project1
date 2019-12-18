package com.epam.finalproject.comand.impl;

import com.epam.finalproject.comand.AttributeName;
import com.epam.finalproject.comand.PathPage;
import com.epam.finalproject.entity.Diet;
import com.epam.finalproject.exception.CommandException;
import com.epam.finalproject.exception.ServiceException;
import com.epam.finalproject.manager.ConfigurationManager;
import com.epam.finalproject.service.DietService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class UserChooseCurrentUserDietCommand implements ActionCommand {

    private static Logger logger = LogManager.getLogger();
    private DietService dietService = new DietService();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        List<Diet> diet = null;
        try {
            diet = dietService.findAllDiets();
        } catch (ServiceException e) {
            logger.error(e);
            throw new CommandException(e);
        }
        request.setAttribute(AttributeName.ALL_DIETS, diet);
        page = ConfigurationManager.getProperty(PathPage.USER_MAIN_PAGE);
        return page;
    }
}