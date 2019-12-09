package com.epam.finalproject.comand.impl;

import com.epam.finalproject.comand.constant.AttributeName;
import com.epam.finalproject.comand.constant.PathPage;
import com.epam.finalproject.exception.CommandException;
import com.epam.finalproject.exception.ServiceException;
import com.epam.finalproject.manager.ConfigurationManager;
import com.epam.finalproject.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class WriteCommentCommand implements ActionCommand {

    private static Logger logger = LogManager.getLogger();
    private UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        boolean isUserCanMakeComment = false;
        String userLogin = (String) session.getAttribute(AttributeName.USER);
        try {
            isUserCanMakeComment = userService.isUserPaid(userLogin);
        } catch (ServiceException e) {
            logger.error(e);
            throw new CommandException(e);
        }
        request.setAttribute(AttributeName.USER_COMMENT, isUserCanMakeComment);
        String page = ConfigurationManager.getProperty(PathPage.USER_MAIN_PAGE);
        return page;
    }
}