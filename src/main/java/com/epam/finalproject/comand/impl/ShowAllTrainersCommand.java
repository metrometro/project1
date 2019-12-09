package com.epam.finalproject.comand.impl;

import com.epam.finalproject.exception.ServiceException;
import com.epam.finalproject.entity.User;
import com.epam.finalproject.comand.constant.AttributeName;
import com.epam.finalproject.comand.constant.PathPage;
import com.epam.finalproject.exception.CommandException;
import com.epam.finalproject.manager.ConfigurationManager;
import com.epam.finalproject.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowAllTrainersCommand implements ActionCommand {

    private static Logger logger = LogManager.getLogger();
    private UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        List<User> users = null;
        try {
            users = userService.findAllTrainers();
        } catch (ServiceException e) {
            logger.error(e);
            throw new CommandException(e);
        }
        request.setAttribute(AttributeName.TRAINERS, users);
        page = ConfigurationManager.getProperty(PathPage.ADMIN_MAIN_PAGE);
        return page;
    }
}