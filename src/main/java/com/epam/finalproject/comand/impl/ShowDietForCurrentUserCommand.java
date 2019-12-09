package com.epam.finalproject.comand.impl;

import com.epam.finalproject.comand.constant.AttributeName;
import com.epam.finalproject.comand.constant.PathPage;
import com.epam.finalproject.entity.Diet;
import com.epam.finalproject.exception.CommandException;
import com.epam.finalproject.exception.ServiceException;
import com.epam.finalproject.manager.ConfigurationManager;
import com.epam.finalproject.service.DietService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ShowDietForCurrentUserCommand implements ActionCommand {

    private static Logger logger = LogManager.getLogger();
    private DietService dietService = new DietService();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        List<Diet> diets = null;
        String page = null;
        HttpSession session = request.getSession();
        String userLogin = (String) session.getAttribute(AttributeName.USER);
        try {
            diets = dietService.findUserDiet(userLogin);

        } catch (ServiceException e) {
            logger.error(e);
            throw new CommandException(e);
        }
        request.setAttribute(AttributeName.DIET, diets);
        page = ConfigurationManager.getProperty(PathPage.USER_MAIN_PAGE);
        return page;
    }
}