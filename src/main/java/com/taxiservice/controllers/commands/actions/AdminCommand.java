package com.taxiservice.controllers.commands.actions;

import com.taxiservice.controllers.commands.Command;
import com.taxiservice.models.services.TariffService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminCommand implements Command {

    private TariffService tariffService ;

    public AdminCommand(TariffService tariffService) {
        this.tariffService = tariffService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        request.getSession().setAttribute("tariffList", tariffService.getAllTariffs());

        return "/WEB-INF/view/admin/adminMenu.jsp";
    }
}
