package com.epam.finalproject.comand.impl;

import com.epam.finalproject.exception.CommandException;
import javax.servlet.http.HttpServletRequest;

public interface ActionCommand {

    String execute(HttpServletRequest request) throws CommandException;
}