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

public class TrainerChooseDietCommand implements ActionCommand {

    private static Logger logger = LogManager.getLogger();
    private DietService dietService = new DietService();
    private final static String PARAM_CURRENT_USER_LOGIN = "currentUserLogin";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        List<Diet> diets = null;
        String login = request.getParameter(PARAM_CURRENT_USER_LOGIN);
        try {
            diets = dietService.findAllDiets();
        } catch (ServiceException e) {
            logger.error(e);
            throw new CommandException(e);
        }
        request.setAttribute(AttributeName.CURRENT_USER_LOGIN, login);
        request.setAttribute(AttributeName.ALL_DIETS, diets);
        page = ConfigurationManager.getProperty(PathPage.TRAINER_MAIN_PAGE);
        return page;
    }
}