package com.taxiservice.controllers.commands.actions;

import com.taxiservice.controllers.commands.Command;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AccountInvoiceCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "/WEB-INF/view/user/accountRefill.jsp";
    }
}
