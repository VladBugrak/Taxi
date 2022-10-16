package com.taxiservice.controllers.commands.actions;

import com.taxiservice.controllers.commands.Command;
import com.taxiservice.models.entities.User;
import com.taxiservice.models.services.UserService;
import com.taxiservice.utils.BundleManagers.ContentManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class AccountRefillAjaxCommand implements Command{

    private UserService userService;

    public AccountRefillAjaxCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        User user = (User) request.getSession().getAttribute("user");

        String lang = (String) request.getSession().getAttribute("lang");

        try (PrintWriter writer = response.getWriter()) {

            try {
                long payment = Long.parseLong(request.getParameter("payment"));

                if (payment>0 && userService.refill(user, payment)) {
                    writer.print(ContentManager.getProperty("payment.success", lang));
                    response.setStatus(200);
                } else {
                    writer.print(ContentManager.getProperty("payment.error", lang));
                    response.setStatus(500);
                }
            }
            catch (NullPointerException | NumberFormatException ex){
                writer.print(ContentManager.getProperty("wrong.form.fields", lang));
                response.setStatus(501);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            response.setStatus(502);
        }

        return null;
    }

}
