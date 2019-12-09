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
import java.util.List;

public class ChoosePaymentCommand implements ActionCommand {

    private static Logger logger = LogManager.getLogger();
    private UserService userService = new UserService();
    private final static String PARAM_USER_LOGIN = "user";
    private final static String PARAM_TRAINER = "trainer";
    private final static String PARAM_VISITS = "visits";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        String login = request.getParameter(PARAM_USER_LOGIN);
        Boolean personalTrainer = true;
        if (request.getParameter(PARAM_TRAINER) == null) {
            personalTrainer = false;
        }
        List<Double> list = null;
        int numberOfVisits = Integer.parseInt(request.getParameter(PARAM_VISITS));
        int price = 0;
        try {
            list = userService.calculateOrder(login, numberOfVisits, personalTrainer);
        } catch (ServiceException e) {
            logger.error(e);
            throw new CommandException(e);
        }
        request.setAttribute(AttributeName.USER, login);
        request.setAttribute(AttributeName.NUMBER_OF_VISITS, numberOfVisits);
        request.setAttribute(AttributeName.PERSONAL_TRAINER, personalTrainer);
        request.setAttribute(AttributeName.PRICE_WITH_DISCOUNT, list);
        page = ConfigurationManager.getProperty(PathPage.PAYMENT_PAGE);
        return page;
    }
}