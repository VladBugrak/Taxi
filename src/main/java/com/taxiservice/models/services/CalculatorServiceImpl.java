package com.taxiservice.models.services;

import com.taxiservice.models.entities.Route;
import com.taxiservice.models.entities.Tariff;

import java.time.LocalDate;

public class CalculatorServiceImpl implements CalculatorService {

    @Override
    public long getDeliveryPrice(Tariff tariff, Route route, int weightGr) {
        return costForWeight(tariff.getCostPerKg(), weightGr)+ costForDistance(tariff.getCostPerKm(), route.getDistanceKm());
    }

    @Override
    public LocalDate getDeliveryDate(Tariff tariff, Route route) {
        return LocalDate.now().plusDays(route.getDistanceKm()/tariff.getPaceDayKm());
    }

    private static long costForDistance(long costPerKm, int distanceKm){
        return costPerKm*distanceKm;
    }
    private static long costForWeight(long costPerKg, int weightGr){
        return (costPerKg*weightGr)/GRAM_IN_KG;
    }

}
