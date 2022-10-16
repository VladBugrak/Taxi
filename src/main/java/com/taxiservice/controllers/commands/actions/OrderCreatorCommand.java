package com.taxiservice.controllers.commands.actions;

import com.taxiservice.controllers.commands.Command;
import com.taxiservice.models.entities.Order;
import com.taxiservice.models.entities.Route;
import com.taxiservice.models.services.RouteService;
import com.taxiservice.models.services.TariffService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Comparator;
import java.util.stream.Collectors;

public class OrderCreatorCommand implements Command {

    private RouteService routeService ;
    private TariffService tariffService ;

    public OrderCreatorCommand(RouteService routeService, TariffService tariffService) {
        this.routeService = routeService;
        this.tariffService = tariffService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        request.setAttribute("orderTypes", Order.Type.values());

        request.setAttribute("routeList", routeService.getAllRoutes().stream()
                .sorted(Comparator.comparing(Route::getRouteStart))
                .collect(Collectors.toList()));

        request.setAttribute("tariffList", tariffService.getAllTariffs());

        return "/WEB-INF/view/user/orderCreator.jsp";

    }
}
