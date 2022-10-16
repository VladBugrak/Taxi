package com.taxiservice.controllers.commands.actions;

import com.taxiservice.controllers.commands.Command;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EmptyCommand implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        return "/WEB-INF/view/home.jsp";

    }
}
