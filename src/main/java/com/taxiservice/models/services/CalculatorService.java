package com.taxiservice.models.services;

import com.taxiservice.models.entities.Route;
import com.taxiservice.models.entities.Tariff;

import java.time.LocalDate;

public interface CalculatorService {
    int GRAM_IN_KG = 1000;

    long getDeliveryPrice(Tariff tariff, Route route, int weight);

    LocalDate getDeliveryDate(Tariff tariff, Route route);
}
