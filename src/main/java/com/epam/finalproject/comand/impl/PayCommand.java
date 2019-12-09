package com.epam.finalproject.comand.impl;

import com.epam.finalproject.comand.dataconst.AttributeName;
import com.epam.finalproject.comand.dataconst.MessageName;
import com.epam.finalproject.comand.dataconst.ParameterName;
import com.epam.finalproject.comand.dataconst.PathPage;
import com.epam.finalproject.exception.CommandException;
import com.epam.finalproject.exception.ServiceException;
import com.epam.finalproject.manager.ConfigurationManager;
import com.epam.finalproject.manager.MessageManager;
import com.epam.finalproject.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class PayCommand implements ActionCommand {

    private static Logger logger = LogManager.getLogger();
    private UserService userService = new UserService();
    private final static String PARAM_USER = "user";
    private final static String PARAM_NUMBER_OF_VISITS = "numberOfVisits";
    private final static String PARAM_PRICE = "price";
    private final static String PARAM_FIRST_CODE = "firstCode";
    private final static String PARAM_SECOND_CODE = "secondCode";
    private final static String PARAM_THIRD_CODE = "thirdCode";
    private final static String PARAM_FOURTH_CODE = "fourthCode";
    private final static String PARAM_CVC_CODE = "cvc";
    private final static String PARAM_PERSONAL_TRAINER = "personalTrainer";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        boolean isOrderCreate = false;
        String page = null;
        List<String> list = new ArrayList<>();

        String firstCode = request.getParameter(PARAM_FIRST_CODE);
        list.add(firstCode);
        String secondCode = request.getParameter(PARAM_SECOND_CODE);
        list.add(secondCode);
        String thirdCode = request.getParameter(PARAM_THIRD_CODE);
        list.add(thirdCode);
        String fourthCode = request.getParameter(PARAM_FOURTH_CODE);
        list.add(fourthCode);
        String cvcCode = request.getParameter(PARAM_CVC_CODE);
        list.add(cvcCode);

        if (!userService.checkCard(list)) {
            request.setAttribute(AttributeName.ERROR_CARD_DATA, MessageManager.getProperty(MessageName.INCORRECT_CARD));
            page = ConfigurationManager.getProperty(PathPage.PAYMENT_PAGE);
            return page;
        }

        String login = request.getParameter(PARAM_USER);
        list.add(login);
        String visits = request.getParameter(PARAM_NUMBER_OF_VISITS);
        list.add(visits);
        String price = request.getParameter(PARAM_PRICE);
        list.add(price);
        String personalTrainer = request.getParameter(PARAM_PERSONAL_TRAINER);
        list.add(personalTrainer);

        try {
            isOrderCreate = userService.createOrder(list);
        } catch (ServiceException e) {
            logger.error(e);
            throw new CommandException(e);
        }

        if (isOrderCreate) {
            request.setAttribute(AttributeName.REDIRECT, ParameterName.REDIRECT);
            page = page = ConfigurationManager.getProperty(PathPage.REDIRECT_RESULT_BUTTON);
        } else {
            request.setAttribute(AttributeName.ERROR_PAYMENT, MessageManager.getProperty(MessageName.INCORRECT_PAYMENT));
            page = ConfigurationManager.getProperty(PathPage.PAYMENT_PAGE);
        }

        return page;
    }
}