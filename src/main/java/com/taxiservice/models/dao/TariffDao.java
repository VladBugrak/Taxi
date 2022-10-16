package com.taxiservice.models.dao;

import com.taxiservice.models.entities.Tariff;

import java.util.List;

public interface TariffDao extends GenericDao<Tariff> {

    List<Tariff> findAvailableByRouteId(long routeId);
}
