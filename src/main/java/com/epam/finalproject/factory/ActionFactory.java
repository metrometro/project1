package com.epam.finalproject.factory;

import com.epam.finalproject.comand.constant.AttributeName;
import com.epam.finalproject.comand.constant.MessageName;
import com.epam.finalproject.comand.CommandType;
import com.epam.finalproject.comand.impl.ActionCommand;
import com.epam.finalproject.comand.impl.EmptyCommand;
import com.epam.finalproject.manager.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;

public class ActionFactory {

    private static Logger logger = LogManager.getLogger();
    private final static String PARAM_COMMAND = "command";

    public ActionCommand defineCommand(HttpServletRequest request) {
        ActionCommand command = null;
        String action = request.getParameter(PARAM_COMMAND);
        if (action == null || action.isEmpty()) {
            command = new EmptyCommand();
            return command;
        }
        try {
            CommandType currentEnum = CommandType.valueOf(action.toUpperCase());
            command = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            logger.error(e);
            request.setAttribute(AttributeName.WRONG_ACTION, action + MessageManager.getProperty(MessageName.WRONG_ACTION));
            command = new EmptyCommand();
        }
        return command;
    }
}