package com.taxiservice.models.services;

import com.taxiservice.models.dto.RouteLocale;
import com.taxiservice.models.entities.Route;

import java.util.List;
import java.util.Optional;

public interface RouteService {

    List<Route> getAllRoutes();

    Optional<Route> getRoute(long id);

    Route create(Route route);

    RouteLocale createWithLocalFields(RouteLocale routeLocale);
}
