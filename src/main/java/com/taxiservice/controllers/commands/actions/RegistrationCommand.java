package com.taxiservice.controllers.commands.actions;

import com.taxiservice.controllers.commands.Command;
import com.taxiservice.controllers.exceptions.NotUniqUserException;
import com.taxiservice.models.entities.User;
import com.taxiservice.models.services.UserService;
import com.taxiservice.utils.BundleManagers.ContentManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RegistrationCommand implements Command {

    private UserService userService;

    public RegistrationCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        String lang = (String) req.getSession().getAttribute("lang");

        String login = req.getParameter("login");

        if (login == null) {
            return "/WEB-INF/view/registration.jsp";
        }

        Map<String, String> fieldsMap = new ConcurrentHashMap<>();

        fieldsMap.put("login", login);
        fieldsMap.put("password", req.getParameter("password"));
        fieldsMap.put("firstname", req.getParameter("firstname"));
        fieldsMap.put("lastname", req.getParameter("lastname"));
        fieldsMap.put("email", req.getParameter("email"));

        Map<String, String> wrongFields = userService.validateFields(fieldsMap);

        User user = new User(fieldsMap.get("login"), fieldsMap.get("password"), fieldsMap.get("firstname"), fieldsMap.get("lastname"), fieldsMap.get("email"), User.Role.USER);

        if (wrongFields.isEmpty()) {
            try {
                userService.create(user);
                req.setAttribute("resultMessage", ContentManager.getProperty("registration.success", lang));
            } catch (NotUniqUserException e) {
                req.setAttribute("wrong_login", ContentManager.getProperty("registration.exception.notUniqUser", lang));
            } catch (RuntimeException re) {
                req.setAttribute("resultMessage", ContentManager.getProperty("exception.runtime", lang));
            }

            return "/WEB-INF/view/registration.jsp";

        } else {
            for (String field : wrongFields.keySet()) {

                req.setAttribute("wrong_" + field, ContentManager.getProperty("wrong." + field, lang));
            }
            req.setAttribute("userDTO", user);

            return "/WEB-INF/view/registration.jsp";
        }

    }
}
