package com.taxiservice.models.dao;

import com.taxiservice.models.entities.AvailableOption;

import java.util.List;

public interface AvailableOptionDao extends GenericDao<AvailableOption> {

    boolean updateOrInsert(List<AvailableOption> optionList);

    AvailableOption findByRouteTariffId(long id_route, long id_tariff);
}
