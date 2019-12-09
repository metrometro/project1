package com.epam.finalproject.comand.trainer;

import com.epam.finalproject.comand.dataconst.AttributeName;
import com.epam.finalproject.comand.dataconst.ParameterName;
import com.epam.finalproject.comand.dataconst.PathPage;
import com.epam.finalproject.comand.impl.ActionCommand;
import com.epam.finalproject.exception.CommandException;
import com.epam.finalproject.exception.ServiceException;
import com.epam.finalproject.manager.ConfigurationManager;
import com.epam.finalproject.service.DietService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class SetPersonalDietCommand implements ActionCommand {

    private static Logger logger = LogManager.getLogger();
    private DietService dietService = new DietService();
    private final static String PARAM_DIET = "diet";
    private final static String PARAM_USER_LOGIN = "userLogin";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        String dietType = request.getParameter(PARAM_DIET);
        String userLogin = request.getParameter(PARAM_USER_LOGIN);

        try {
            dietService.setDiet(userLogin, dietType);
        } catch (ServiceException e) {
            logger.error(e);
            throw new CommandException(e);
        }

        request.setAttribute(AttributeName.REDIRECT, ParameterName.REDIRECT);
        page = ConfigurationManager.getProperty(PathPage.REDIRECT_SHOW_USER_DIET) + userLogin;
        return page;
    }
}
