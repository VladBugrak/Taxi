package com.taxiservice.models.services;

import com.taxiservice.models.dao.AvailableOptionDao;
import com.taxiservice.models.dao.DaoFactoryAbst;
import com.taxiservice.models.entities.AvailableOption;

import java.util.List;
import java.util.Optional;

public class AvailableOptionServiceImpl implements AvailableOptionService {

    private DaoFactoryAbst daoFactoryAbst = DaoFactoryAbst.getInstance();

    @Override
    public List<AvailableOption> getAllAvailableOptions() {
        try (AvailableOptionDao dao = daoFactoryAbst.createOptionDao()) {
            return dao.findAll();
        }
    }

    @Override
    public Optional<AvailableOption> getAvailableOption(long id) {
        try (AvailableOptionDao dao = daoFactoryAbst.createOptionDao()) {
            return Optional.ofNullable(dao.findById(id));
        }
    }

    @Override
    public AvailableOption create(AvailableOption availableOption) {
        try (AvailableOptionDao dao = daoFactoryAbst.createOptionDao()) {
            return dao.create(availableOption);
        }
    }

    @Override
    public Optional<AvailableOption> getByRouteTariffId(long id_route, long id_tariff) {
        try (AvailableOptionDao dao = daoFactoryAbst.createOptionDao()) {
            return Optional.ofNullable(dao.findByRouteTariffId(id_route, id_tariff));
        }
    }

    @Override
    public boolean updateOrInsert(List<AvailableOption> optionList){
        try (AvailableOptionDao dao = daoFactoryAbst.createOptionDao()) {
            return dao.updateOrInsert(optionList);
        }
    }
}


