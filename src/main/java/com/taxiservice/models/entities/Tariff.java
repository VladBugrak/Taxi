package com.taxiservice.models.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Tariff implements Serializable {

    private long id;
    private String name;
    private long costPerKm;
    private long costPerKg;
    private int paceDayKm;

    private List<Route> routeList = new ArrayList<>();

    public Tariff() {
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public long getCostPerKm() {
        return costPerKm;
    }
    public void setCostPerKm(long costPerKm) {
        this.costPerKm = costPerKm;
    }

    public long getCostPerKg() {
        return costPerKg;
    }
    public void setCostPerKg(long costPerKg) {
        this.costPerKg = costPerKg;
    }

    public int getPaceDayKm() {
        return paceDayKm;
    }
    public void setPaceDayKm(int paceDayKm) {
        this.paceDayKm = paceDayKm;
    }

    public List<Route> getRouteList() {
        return routeList;
    }
    public void setRouteList(List<Route> routeList) {
        this.routeList = routeList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tariff tariff = (Tariff) o;
        return Objects.equals(name, tariff.name)&&
                costPerKm == tariff.costPerKm &&
                costPerKg == tariff.costPerKg &&
                paceDayKm == tariff.paceDayKm;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, costPerKm, costPerKg, paceDayKm);
    }


}
