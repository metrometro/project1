package com.epam.finalproject.comand.impl;

import com.epam.finalproject.comand.constant.AttributeName;
import com.epam.finalproject.comand.constant.MessageName;
import com.epam.finalproject.comand.constant.ParameterName;
import com.epam.finalproject.comand.constant.PathPage;
import com.epam.finalproject.entity.RoleType;
import com.epam.finalproject.entity.User;
import com.epam.finalproject.exception.CommandException;
import com.epam.finalproject.exception.ServiceException;
import com.epam.finalproject.manager.ConfigurationManager;
import com.epam.finalproject.manager.MessageManager;
import com.epam.finalproject.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import static com.epam.finalproject.entity.RoleType.*;

public class LoginCommand implements ActionCommand {

    private static Logger logger = LogManager.getLogger();
    private UserService userService = new UserService();
    private final static String PARAM_LOGIN = "login";
    private final static String PARAM_PASSWORD = "password";
    private final static String PARAM_LOCAL = "local";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        String login = request.getParameter(PARAM_LOGIN);
        String pass = request.getParameter(PARAM_PASSWORD);
        String local = request.getParameter(PARAM_LOCAL);
        User user = null;
        try {
            user = userService.checkLoginAndPass(login, pass);
        } catch (ServiceException e) {
            logger.error(e);
            throw new CommandException(e);
        }
        if (user != null) {
            if (!user.isStatus()) {
                request.setAttribute(AttributeName.NOT_ACTIVE_ACCOUNT, MessageManager.getProperty(MessageName.YOUR_ACCOUNT_IS_NOT_ACTIVE));
                page = ConfigurationManager.getProperty(PathPage.LOGIN_PAGE);
                return page;
            }
            HttpSession session = request.getSession();
            session.setAttribute(AttributeName.USER, login);
            session.setAttribute(AttributeName.STATUS, user.isStatus());
            RoleType roleType = valueOf(user.getRole().toUpperCase());
            switch (roleType) {
                case USER:
                    page = ConfigurationManager.getProperty(PathPage.USER_MAIN_PAGE);
                    session.setAttribute(AttributeName.ROLE, ParameterName.USER_ROLE);
                    session.setAttribute(AttributeName.LOCAL, local);
                    break;
                case TRAINER:
                    page = ConfigurationManager.getProperty(PathPage.TRAINER_MAIN_PAGE);
                    session.setAttribute(AttributeName.ROLE, ParameterName.TRAINER_ROLE);
                    session.setAttribute(AttributeName.LOCAL, local);
                    break;
                case ADMINISTRATOR:
                    page = ConfigurationManager.getProperty(PathPage.ADMIN_MAIN_PAGE);
                    session.setAttribute(AttributeName.ROLE, ParameterName.ADMINISTRATOR_ROLE);
                    session.setAttribute(AttributeName.LOCAL, local);
                    break;
                default:
                    request.setAttribute(AttributeName.WRONG_ACTION, MessageManager.getProperty(MessageName.WRONG_ACTION));
                    page = ConfigurationManager.getProperty(PathPage.LOGIN_PAGE);
            }
        } else {
            request.setAttribute(AttributeName.ERROR_LOGIN_OR_PASSWORD, MessageManager.getProperty(MessageName.LOGIN_ERROR));
            page = ConfigurationManager.getProperty(PathPage.LOGIN_PAGE);
        }
        return page;
    }
}