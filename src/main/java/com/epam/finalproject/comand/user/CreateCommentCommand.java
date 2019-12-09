package com.epam.finalproject.comand.user;

import com.epam.finalproject.comand.dataconst.AttributeName;
import com.epam.finalproject.comand.dataconst.ParameterName;
import com.epam.finalproject.comand.dataconst.PathPage;
import com.epam.finalproject.comand.impl.ActionCommand;
import com.epam.finalproject.exception.CommandException;
import com.epam.finalproject.exception.ServiceException;
import com.epam.finalproject.manager.ConfigurationManager;
import com.epam.finalproject.service.DietService;
import com.epam.finalproject.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CreateCommentCommand implements ActionCommand {

    private static Logger logger = LogManager.getLogger();
    private UserService userService = new UserService();
    private final static String PARAM_COMMENT = "comment";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String userLogin = (String) session.getAttribute(AttributeName.USER);
        String comment = request.getParameter(PARAM_COMMENT);
        String page = null;

        try {
            userService.createComment(userLogin, comment);
        } catch (ServiceException e) {
            logger.error(e);
            throw new CommandException(e);
        }
            System.out.println(comment);


        request.setAttribute(AttributeName.REDIRECT, ParameterName.REDIRECT);
        page = ConfigurationManager.getProperty(PathPage.REDIRECT_SHOW_CURRENT_USER_COMMENTS);
        return page;
    }
}
