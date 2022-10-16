package com.taxiservice.models.dto;

import com.taxiservice.models.entities.Route;

import java.util.HashMap;
import java.util.Map;

public class RouteLocale {

    private Route route;

    private Map<String, LocalFields> localFieldsMap = new HashMap<>();

    public class LocalFields {
        private String routeStart;
        private String routeEnd;

        public String getRouteStart() {
            return routeStart;
        }
        public void setRouteStart(String routeStart) {
            this.routeStart = routeStart;
        }

        public String getRouteEnd() {
            return routeEnd;
        }
        public void setRouteEnd(String routeEnd) {
            this.routeEnd = routeEnd;
        }

        public LocalFields() {
        }
        public LocalFields(String routeStart, String routeEnd) {
            this.routeStart = routeStart;
            this.routeEnd = routeEnd;
        }
    }

    public Route getRoute() {
        return route;
    }
    public void setRoute(Route route) {
        this.route = route;
    }

    public Map<String, LocalFields> getLocalFieldsMap() {
        return localFieldsMap;
    }
    public void setLocalFieldsMap(Map<String, LocalFields> localFieldsMap) {
        this.localFieldsMap = localFieldsMap;
    }

    public RouteLocale() {
    }
    public RouteLocale(Route route) {
        this.route = route;
    }
    public RouteLocale(Route route, Map<String, LocalFields> localFieldsMap) {
        this.route = route;
        this.localFieldsMap = localFieldsMap;
    }
}
