package com.epam.finalproject.controller;

import com.epam.finalproject.comand.constant.ParameterName;
import com.epam.finalproject.comand.impl.ActionCommand;
import com.epam.finalproject.comand.constant.AttributeName;
import com.epam.finalproject.comand.constant.MessageName;
import com.epam.finalproject.comand.constant.PathPage;
import com.epam.finalproject.exception.CommandException;
import com.epam.finalproject.factory.ActionFactory;
import com.epam.finalproject.manager.ConfigurationManager;
import com.epam.finalproject.manager.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
public class Controller extends HttpServlet {

    private static Logger logger = LogManager.getLogger();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        ActionFactory client = new ActionFactory();
        ActionCommand command = client.defineCommand(request);

        try {
            page = command.execute(request);
        } catch (CommandException e) {
            logger.error(e);
        }

        if (page != null) {
            if (request.getAttribute(AttributeName.REDIRECT) != null &&
                    (request.getAttribute(AttributeName.REDIRECT)).equals(ParameterName.REDIRECT)) {
                response.sendRedirect(page);
            } else {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
                dispatcher.forward(request, response);
            }
        } else {
            page = ConfigurationManager.getProperty(PathPage.INDEX_PAGE);
            request.getSession().setAttribute(AttributeName.NULL_PAGE, MessageManager.getProperty(MessageName.NULL_PAGE));
            response.sendRedirect(request.getContextPath() + page);
        }
    }
}