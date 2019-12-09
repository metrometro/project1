package com.epam.finalproject.comand.user;

import com.epam.finalproject.comand.dataconst.AttributeName;
import com.epam.finalproject.comand.dataconst.PathPage;
import com.epam.finalproject.comand.impl.ActionCommand;
import com.epam.finalproject.entity.Diet;
import com.epam.finalproject.entity.User;
import com.epam.finalproject.exception.CommandException;
import com.epam.finalproject.exception.ServiceException;
import com.epam.finalproject.manager.ConfigurationManager;
import com.epam.finalproject.service.DietService;
import com.epam.finalproject.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class ShowCurrentUserStateCommand implements ActionCommand {

    private static Logger logger = LogManager.getLogger();
    private UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        List<String> list = new ArrayList<>();;
        String page = null;
        HttpSession session = request.getSession();
        String userLogin = (String) session.getAttribute(AttributeName.USER);

        try {
            list = userService.showCurrentUserStatus(userLogin);
        } catch (ServiceException e) {
            logger.error(e);
            throw new CommandException(e);
        }

        request.setAttribute(AttributeName.USER_STATE, list);
        page = ConfigurationManager.getProperty(PathPage.USER_MAIN_PAGE);
        return page;
    }
}
