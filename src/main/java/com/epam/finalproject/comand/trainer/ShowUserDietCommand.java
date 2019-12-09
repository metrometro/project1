package com.epam.finalproject.comand.trainer;

import com.epam.finalproject.comand.dataconst.AttributeName;
import com.epam.finalproject.comand.dataconst.PathPage;
import com.epam.finalproject.comand.impl.ActionCommand;
import com.epam.finalproject.entity.Diet;
import com.epam.finalproject.exception.CommandException;
import com.epam.finalproject.exception.ServiceException;
import com.epam.finalproject.manager.ConfigurationManager;
import com.epam.finalproject.service.DietService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowUserDietCommand implements ActionCommand {

    private static Logger logger = LogManager.getLogger();
    private DietService dietService = new DietService();
    private final static String PARAM_USER_LOGIN = "userLogin";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        List<Diet> diets = null;
        String page = null;
        String userLogin = request.getParameter(PARAM_USER_LOGIN);

        try {
            diets = dietService.findUserDiet(userLogin);
        } catch (ServiceException e) {
            logger.error(e);
            throw new CommandException(e);
        }

        request.setAttribute(AttributeName.USER_DIET, diets);
        request.setAttribute(AttributeName.CURRENT_USER_LOGIN, userLogin);
        page = ConfigurationManager.getProperty(PathPage.TRAINER_MAIN_PAGE);
        return page;
    }
}
