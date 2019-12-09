package com.epam.finalproject.comand.user;

import com.epam.finalproject.comand.dataconst.AttributeName;
import com.epam.finalproject.comand.dataconst.MessageName;
import com.epam.finalproject.comand.dataconst.PathPage;
import com.epam.finalproject.comand.impl.ActionCommand;
import com.epam.finalproject.exception.CommandException;
import com.epam.finalproject.exception.ServiceException;
import com.epam.finalproject.manager.ConfigurationManager;
import com.epam.finalproject.manager.MessageManager;
import com.epam.finalproject.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;

public class PaymentFormCommand implements ActionCommand {

    private static Logger logger = LogManager.getLogger();
    private UserService userService = new UserService();
    private final static String PARAM_USER = "user";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String login = request.getParameter(PARAM_USER);
        String page = null;

        try {
            if (userService.isUserPaid(login)) {
                request.setAttribute(AttributeName.ERROR_USER_ALREADY_PAID, MessageManager.getProperty(MessageName.USER_ALREADY_PAID));
                page = ConfigurationManager.getProperty(PathPage.USER_MAIN_PAGE);
            } else {
                request.setAttribute(AttributeName.USER, login);
                page = ConfigurationManager.getProperty(PathPage.PAYMENT_PAGE);
            }
        } catch (ServiceException e) {
            logger.error(e);
            throw new CommandException(e);
        }

        return page;
    }
}