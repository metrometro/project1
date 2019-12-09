package com.epam.finalproject.comand.impl;

import com.epam.finalproject.comand.constant.AttributeName;
import com.epam.finalproject.comand.constant.PathPage;
import com.epam.finalproject.entity.Exercise;
import com.epam.finalproject.exception.CommandException;
import com.epam.finalproject.exception.ServiceException;
import com.epam.finalproject.manager.ConfigurationManager;
import com.epam.finalproject.service.ExerciseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowUserExercisesCommand implements ActionCommand {

    private static Logger logger = LogManager.getLogger();
    private ExerciseService exerciseService = new ExerciseService();
    private final static String PARAM_USER_LOGIN = "userLogin";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        List<Exercise> exercises = null;
        String page = null;
        String userLogin = request.getParameter(PARAM_USER_LOGIN);
        try {
            exercises = exerciseService.findUserExercises(userLogin);
        } catch (ServiceException e) {
            logger.error(e);
            throw new CommandException(e);
        }
        request.setAttribute(AttributeName.USER_EXERCISES, exercises);
        request.setAttribute(AttributeName.CURRENT_USER_LOGIN, userLogin);
        page = ConfigurationManager.getProperty(PathPage.TRAINER_MAIN_PAGE);
        return page;
    }
}