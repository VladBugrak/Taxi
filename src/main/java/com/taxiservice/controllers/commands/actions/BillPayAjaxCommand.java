package com.taxiservice.controllers.commands.actions;

import com.taxiservice.controllers.commands.Command;
import com.taxiservice.controllers.exceptions.NotEnoughMoneyException;
import com.taxiservice.controllers.exceptions.SettleUpDuplicationException;
import com.taxiservice.models.entities.Bill;
import com.taxiservice.models.services.BillService;
import com.taxiservice.utils.BundleManagers.ContentManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

public class BillPayAjaxCommand implements Command {

    private BillService billService;

    public BillPayAjaxCommand(BillService billService) {
        this.billService = billService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String lang = (String) request.getSession().getAttribute("lang");

        long billId = Long.parseLong(request.getParameter("billId"));

        Optional<Bill> bill = billService.getById(billId);

        bill.ifPresent(
                b -> {
                    try (PrintWriter writer = response.getWriter()) {
                        try {
                            if (billService.settleUp(b))
                                response.setStatus(200);
                        } catch (SettleUpDuplicationException e) {
                            writer.print(ContentManager.getProperty("exception.SettleUpDuplicationException", lang));
                            response.setStatus(500);
                        } catch (NotEnoughMoneyException e) {
                            writer.print(ContentManager.getProperty("exception.NotEnoughMoneyException", lang));
                            response.setStatus(500);
                        } catch (RuntimeException e) {
                            writer.print(ContentManager.getProperty("exception.runtime", lang));
                            response.setStatus(500);
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );

        return null;
    }
}
