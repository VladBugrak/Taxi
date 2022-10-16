package com.taxiservice.models.dto;

import com.taxiservice.models.entities.Tariff;

import java.util.HashMap;
import java.util.Map;

public class TariffLocale {

    private Tariff tariff;

    private Map<String, RouteLocale.LocalFields> localFieldsMap = new HashMap<>();

    public class LocalFields {
        private String name;

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }

        public LocalFields() {
        }
        public LocalFields(String name) {
            this.name = name;
        }
    }

    public Tariff getTariff() {
        return tariff;
    }
    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }

    public Map<String, RouteLocale.LocalFields> getLocalFieldsMap() {
        return localFieldsMap;
    }
    public void setLocalFieldsMap(Map<String, RouteLocale.LocalFields> localFieldsMap) {
        this.localFieldsMap = localFieldsMap;
    }

    public TariffLocale() {
    }
    public TariffLocale(Tariff tariff) {
        this.tariff = tariff;
    }
    public TariffLocale(Tariff tariff, Map<String, RouteLocale.LocalFields> localFieldsMap) {
        this.tariff = tariff;
        this.localFieldsMap = localFieldsMap;
    }
}
