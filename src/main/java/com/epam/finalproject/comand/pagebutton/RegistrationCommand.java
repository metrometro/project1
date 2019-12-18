package com.epam.finalproject.comand.pagebutton;

import com.epam.finalproject.comand.impl.ActionCommand;
import com.epam.finalproject.comand.PathPage;
import com.epam.finalproject.manager.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;

public class RegistrationCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty(PathPage.REGISTRATION_PAGE);
        return page;
    }
}