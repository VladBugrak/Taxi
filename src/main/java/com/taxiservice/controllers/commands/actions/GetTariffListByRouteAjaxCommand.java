package com.taxiservice.controllers.commands.actions;

import com.google.gson.Gson;
import com.taxiservice.controllers.commands.Command;
import com.taxiservice.models.entities.Tariff;
import com.taxiservice.models.services.TariffService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class GetTariffListByRouteAjaxCommand implements Command {

    private TariffService tariffService;

    public GetTariffListByRouteAjaxCommand(TariffService tariffService) {
        this.tariffService = tariffService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        String routeId = req.getParameter("routeId");

        List<Tariff> tariffList = tariffService.findAvailableByRouteId(Long.parseLong(routeId));

        resp.setContentType("application/json");

        Gson gson = new Gson();

        String stringJson = gson.toJson(tariffList);

        try (PrintWriter writer = resp.getWriter()) {

            writer.print(stringJson);

        } catch (IOException e) {

            resp.setStatus(500);

        }

        return null;
    }
}

