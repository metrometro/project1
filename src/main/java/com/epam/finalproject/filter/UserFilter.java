package com.epam.finalproject.filter;

import com.epam.finalproject.comand.constant.AttributeName;
import com.epam.finalproject.comand.constant.PathPage;
import com.epam.finalproject.entity.RoleType;
import com.epam.finalproject.manager.ConfigurationManager;
import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebFilter(filterName = "UserFilter", urlPatterns = { "/jsp/user/*" }, dispatcherTypes = { DispatcherType.FORWARD,
        DispatcherType.REQUEST })
public class UserFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String role = (String) httpRequest.getSession().getAttribute(AttributeName.ROLE);

        if (role == null || !role.toUpperCase().equals(RoleType.USER.toString())) {
            RequestDispatcher dispatcher = httpRequest.getServletContext()
                    .getRequestDispatcher(ConfigurationManager.getProperty(PathPage.LOGIN_PAGE));
            dispatcher.forward(httpRequest, httpResponse);
        } else {
            chain.doFilter(httpRequest, httpResponse);
        }
    }

    public void destroy() {
    }
}