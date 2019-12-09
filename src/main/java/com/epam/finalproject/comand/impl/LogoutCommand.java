package com.epam.finalproject.comand.impl;

import com.epam.finalproject.comand.constant.PathPage;
import com.epam.finalproject.manager.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty(PathPage.INDEX_PAGE);
        request.getSession().invalidate();
        return page;
    }
}