package com.epam.finalproject.comand.trainer;

import com.epam.finalproject.comand.dataconst.AttributeName;
import com.epam.finalproject.comand.dataconst.ParameterName;
import com.epam.finalproject.comand.dataconst.PathPage;
import com.epam.finalproject.comand.impl.ActionCommand;
import com.epam.finalproject.exception.CommandException;
import com.epam.finalproject.exception.ServiceException;
import com.epam.finalproject.manager.ConfigurationManager;
import com.epam.finalproject.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class MarkPersonalUsersVisitCommand implements ActionCommand {

    private static Logger logger = LogManager.getLogger();
    private UserService userService = new UserService();
    private final static String PARAM_USERS_LOGIN = "users";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        String[] usersLogin = request.getParameterValues(PARAM_USERS_LOGIN);
        HttpSession session = request.getSession();
        String trainerLogin = (String) session.getAttribute(AttributeName.USER);

        try {
            userService.markUsersVisit(usersLogin);
        } catch (ServiceException e) {
            logger.error(e);
            throw new CommandException(e);
        }

        request.setAttribute(AttributeName.REDIRECT, ParameterName.REDIRECT);
        page = ConfigurationManager.getProperty(PathPage.REDIRECT_CHOOSE_PERSONAL_USERS_MARK);
        return page;
    }
}
