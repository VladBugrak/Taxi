package com.taxiservice.controllers.commands.actions;

import com.taxiservice.controllers.commands.Command;
import com.taxiservice.models.entities.Tariff;
import com.taxiservice.models.services.TariffService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Optional;

public class TariffEditAjaxCommand implements Command {

    private TariffService tariffService;

    public TariffEditAjaxCommand(TariffService tariffService) {
        this.tariffService = tariffService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        long tariffId = Long.parseLong((req.getParameter("tariffId")));
        long costPerKm = Long.parseLong(req.getParameter("costPerKm"));
        long costPerKg = Long.parseLong(req.getParameter("costPerKg"));
        int paceDayKm = Integer.parseInt(req.getParameter("paceDayKm"));

        Optional<Tariff> optionalTariff = tariffService.getTariff(tariffId);

        if(optionalTariff.isPresent()){

            Tariff tariff = optionalTariff.get();

            tariff.setCostPerKm(costPerKm);
            tariff.setCostPerKg(costPerKg);
            tariff.setPaceDayKm(paceDayKm);

            if(tariffService.update(tariff)) {
                resp.setStatus(200);
            }
            else resp.setStatus(500);
        }
        else
            resp.setStatus(501);

        return null;
    }
}


