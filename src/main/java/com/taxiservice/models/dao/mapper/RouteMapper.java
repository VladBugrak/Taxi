package com.taxiservice.models.dao.mapper;

import com.taxiservice.models.entities.Route;
import com.taxiservice.utils.BundleManagers.ContentManager;
import com.taxiservice.utils.LocaleThreadLocal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class RouteMapper implements ObjectMapper<Route> {

    @Override
    public Route extractFromResultSet(ResultSet rs) throws SQLException {

        Route route = new Route();

        route.setId(rs.getLong("id_route"));
        route.setRouteStart(rs.getString(ContentManager.getProperty("table.route.column.route_start", LocaleThreadLocal.getLocale().getLanguage())));
        route.setRouteEnd(rs.getString(ContentManager.getProperty("table.route.column.route_end", LocaleThreadLocal.getLocale().getLanguage())));
        route.setDistanceKm(rs.getInt("distance_km"));

        return route;
    }

    @Override
    public Route makeUnique(Map<Long, Route> cache, Route route) {

        cache.putIfAbsent(route.getId(), route);

        return cache.get(route.getId());
    }
}
