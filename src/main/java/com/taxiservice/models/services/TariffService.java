package com.taxiservice.models.services;

import com.taxiservice.models.entities.Tariff;

import java.util.List;
import java.util.Optional;

public interface TariffService {

    List<Tariff> getAllTariffs();

    Optional<Tariff> getTariff(long id);

    Tariff create(Tariff tariff);

    List<Tariff> findAvailableByRouteId(long routeId);

    boolean update(Tariff tariff);
}
