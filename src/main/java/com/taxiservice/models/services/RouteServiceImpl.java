package com.taxiservice.models.services;

import com.taxiservice.models.dao.DaoFactoryAbst;
import com.taxiservice.models.dao.RouteDao;
import com.taxiservice.models.dto.RouteLocale;
import com.taxiservice.models.entities.Route;

import java.util.List;
import java.util.Optional;

public class RouteServiceImpl implements RouteService {

    private DaoFactoryAbst daoFactoryAbst = DaoFactoryAbst.getInstance();

    @Override
    public List<Route> getAllRoutes() {
        try (RouteDao dao = daoFactoryAbst.createRouteDao()) {
            return dao.findAll();
        }
    }

    @Override
    public Optional<Route> getRoute(long id) {
        try (RouteDao dao = daoFactoryAbst.createRouteDao()) {
            return Optional.ofNullable(dao.findById(id));
        }
    }

    @Override
    public Route create(Route route) {
        try (RouteDao dao = daoFactoryAbst.createRouteDao()) {
            return dao.create(route);
        }
    }

    @Override
    public RouteLocale createWithLocalFields(RouteLocale routeLocale){
        try (RouteDao dao = daoFactoryAbst.createRouteDao()) {
            return dao.createWithLocalFields(routeLocale);
        }
    }
}
