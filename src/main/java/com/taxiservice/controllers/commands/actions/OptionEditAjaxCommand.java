package com.taxiservice.controllers.commands.actions;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.taxiservice.controllers.commands.Command;
import com.taxiservice.models.entities.AvailableOption;
import com.taxiservice.models.entities.Route;
import com.taxiservice.models.entities.Tariff;
import com.taxiservice.models.services.AvailableOptionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;

public class OptionEditAjaxCommand implements Command {

    private AvailableOptionService availableOptionService;

    public OptionEditAjaxCommand(AvailableOptionService availableOptionService) {
        this.availableOptionService = availableOptionService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        List<AvailableOption> optionList = new ArrayList<>();

        long routeId = Long.parseLong((req.getParameter("routeId")));

        Route route = new Route();

        route.setId(routeId);

        JsonElement jsonElementArray = new JsonParser().parse(req.getParameter("tariffArray"));

        for(JsonElement json: jsonElementArray.getAsJsonArray()){
            long tariffId = json.getAsJsonObject().get("id").getAsLong();
            boolean isAvailable = json.getAsJsonObject().get("isAvailable").getAsBoolean();
            Tariff tariff = new Tariff();
            tariff.setId(tariffId);
            optionList.add(new AvailableOption(route, tariff, isAvailable));
        }
        try{
            availableOptionService.updateOrInsert(optionList);
            resp.setStatus(200);
        }
        catch (RuntimeException e){
            resp.setStatus(500);
        }

        return null;
    }
}

