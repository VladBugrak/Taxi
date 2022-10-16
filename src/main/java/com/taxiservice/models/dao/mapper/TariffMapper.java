package com.taxiservice.models.dao.mapper;

import com.taxiservice.models.entities.Tariff;
import com.taxiservice.utils.BundleManagers.ContentManager;
import com.taxiservice.utils.LocaleThreadLocal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class TariffMapper implements ObjectMapper<Tariff> {
    @Override
    public Tariff extractFromResultSet(ResultSet rs) throws SQLException {

        Tariff tariff = new Tariff();

        tariff.setId(rs.getLong("id_tariff"));
        tariff.setName(rs.getString(ContentManager.getProperty("table.tariff.column.name", LocaleThreadLocal.getLocale().getLanguage())));
        tariff.setCostPerKm(rs.getLong("cost_per_km"));
        tariff.setCostPerKg(rs.getLong("cost_per_kg"));
        tariff.setPaceDayKm(rs.getInt("pace_day_km"));

        return tariff;

    }

    @Override
    public Tariff makeUnique(Map<Long, Tariff> cache, Tariff tariff) {

        cache.putIfAbsent(tariff.getId(), tariff);

        return cache.get(tariff.getId());
    }
}
