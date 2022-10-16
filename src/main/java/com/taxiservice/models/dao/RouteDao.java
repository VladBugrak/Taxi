package com.taxiservice.models.dao;

import com.taxiservice.models.dto.RouteLocale;
import com.taxiservice.models.entities.Route;

public interface RouteDao extends GenericDao<Route> {

    RouteLocale createWithLocalFields(RouteLocale routeLocale);

}
