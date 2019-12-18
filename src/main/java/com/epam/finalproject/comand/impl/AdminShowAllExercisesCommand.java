package com.epam.finalproject.comand.impl;

import com.epam.finalproject.comand.AttributeName;
import com.epam.finalproject.comand.PathPage;
import com.epam.finalproject.entity.Exercise;
import com.epam.finalproject.exception.CommandException;
import com.epam.finalproject.exception.ServiceException;
import com.epam.finalproject.manager.ConfigurationManager;
import com.epam.finalproject.service.ExerciseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AdminShowAllExercisesCommand implements ActionCommand {

    private static Logger logger = LogManager.getLogger();
    private ExerciseService exerciseService = new ExerciseService();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        List<Exercise> users = null;
        String page = null;
        try {
            users = exerciseService.findAllExercises();
        } catch (ServiceException e) {
            logger.error(e);
            throw new CommandException(e);
        }
        request.setAttribute(AttributeName.ALL_EXERCISES, users);
        page = ConfigurationManager.getProperty(PathPage.ADMIN_MAIN_PAGE);
        return page;
    }
}