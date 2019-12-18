package com.epam.finalproject.comand.impl;

import com.epam.finalproject.comand.constant.ParameterName;
import com.epam.finalproject.comand.constant.PathPage;
import com.epam.finalproject.exception.ServiceException;
import com.epam.finalproject.comand.constant.AttributeName;
import com.epam.finalproject.exception.CommandException;
import com.epam.finalproject.manager.ConfigurationManager;
import com.epam.finalproject.service.ExerciseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;

public class TrainerSetExercisesForUserCommand implements ActionCommand {

    private static Logger logger = LogManager.getLogger();
    private ExerciseService exerciseService = new ExerciseService();
    private final static String PARAM_EXERCISES = "exercises";
    private final static String PARAM_USER_LOGIN = "userLogin";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        String[] exercises = request.getParameterValues(PARAM_EXERCISES);
        String userLogin = request.getParameter(PARAM_USER_LOGIN);
        try {
            exerciseService.setExercises(userLogin, exercises);
        } catch (ServiceException e) {
            logger.error(e);
            throw new CommandException(e);
        }
        request.setAttribute(AttributeName.REDIRECT, ParameterName.REDIRECT);
        page = ConfigurationManager.getProperty(PathPage.REDIRECT_SHOW_ALL_USERS_WITHOUT_EXERCISES);
        return page;
    }
}