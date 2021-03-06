package com.epam.finalproject.comand.impl;

import com.epam.finalproject.comand.AttributeName;
import com.epam.finalproject.comand.ParameterName;
import com.epam.finalproject.comand.PathPage;
import com.epam.finalproject.exception.CommandException;
import com.epam.finalproject.exception.ServiceException;
import com.epam.finalproject.manager.ConfigurationManager;
import com.epam.finalproject.service.ExerciseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;

public class AdminCreateNewExercisesCommand implements ActionCommand {

    private static Logger logger = LogManager.getLogger();
    private ExerciseService exerciseService = new ExerciseService();
    private final static String PARAM_EXERCISE = "exercise";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String exercise = request.getParameter(PARAM_EXERCISE);
        String page = null;
        try {
            exerciseService.createExercise(exercise);
        } catch (ServiceException e) {
            logger.error(e);
            throw new CommandException(e);
        }
        request.setAttribute(AttributeName.REDIRECT, ParameterName.REDIRECT);
        page = ConfigurationManager.getProperty(PathPage.REDIRECT_SHOW_ALL_EXERCISES);
        return page;
    }
}