package com.epam.finalproject.comand.impl;

import com.epam.finalproject.comand.dataconst.AttributeName;
import com.epam.finalproject.comand.dataconst.PathPage;
import com.epam.finalproject.entity.Diet;
import com.epam.finalproject.entity.Exercise;
import com.epam.finalproject.exception.CommandException;
import com.epam.finalproject.exception.ServiceException;
import com.epam.finalproject.manager.ConfigurationManager;
import com.epam.finalproject.service.DietService;
import com.epam.finalproject.service.ExerciseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowAllDietsCommand implements ActionCommand {

    private static Logger logger = LogManager.getLogger();
    private DietService dietService = new DietService();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        List<Diet> diets = null;
        String page = null;

        try {
            diets = dietService.findAllDiets();
        } catch (ServiceException e) {
            logger.error(e);
            throw new CommandException(e);
        }

        request.setAttribute(AttributeName.ALL_DIETS, diets);
        page = ConfigurationManager.getProperty(PathPage.ADMIN_MAIN_PAGE);
        return page;
    }
}
