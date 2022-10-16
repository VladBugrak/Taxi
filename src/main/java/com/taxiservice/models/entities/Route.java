package com.taxiservice.models.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Route {

    private long id;
    private String routeStart;
    private String routeEnd;
    private int distanceKm;

    private List<Tariff> tariffList = new ArrayList<>();

    public Route() {
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

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

    public int getDistanceKm() {
        return distanceKm;
    }
    public void setDistanceKm(int distanceKm) {
        this.distanceKm = distanceKm;
    }

    public List<Tariff> getTariffList() {
        return tariffList;
    }
    public void setTariffList(List<Tariff> tariffList) {
        this.tariffList = tariffList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return distanceKm == route.distanceKm &&
                Objects.equals(routeStart, route.routeStart) &&
                Objects.equals(routeEnd, route.routeEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(routeStart, routeEnd, distanceKm);
    }

}
