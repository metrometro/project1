package com.epam.finalproject.comand.pagebutton;

import com.epam.finalproject.comand.constant.PathPage;
import com.epam.finalproject.comand.impl.ActionCommand;
import com.epam.finalproject.manager.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty(PathPage.LOGIN_PAGE);
        return page;
    }
}