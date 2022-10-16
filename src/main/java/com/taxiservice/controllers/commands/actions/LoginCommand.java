package com.taxiservice.controllers.commands.actions;

import com.taxiservice.controllers.commands.Command;
import com.taxiservice.controllers.exceptions.RoleAccessDeniedCommandException;
import com.taxiservice.controllers.exceptions.WrongCommandException;
import com.taxiservice.models.entities.User;
import com.taxiservice.models.services.UserService;
import com.taxiservice.utils.BundleManagers.ContentManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class LoginCommand implements Command {

    private UserService userService ;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws RoleAccessDeniedCommandException, WrongCommandException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (req.getSession().getAttribute("user") != null) {
            throw new WrongCommandException("User already logged");
        }

        if (login == null || password == null) {
            return "/WEB-INF/view/login.jsp";
        }

        Optional<User> user = userService.login(login, password);

        if (user.isPresent()) {

            Map<String, HttpSession> loggedUsers = (ConcurrentHashMap<String, HttpSession>) req.getServletContext().getAttribute("loggedUsers");
            if (loggedUsers.containsKey(user.get().getLogin())) {
                throw new RoleAccessDeniedCommandException("User already logged !");
            }

            loggedUsers.put(user.get().getLogin(), req.getSession());
            req.getServletContext().setAttribute("loggedUsers", loggedUsers);

            user.get().setPassword(null);
            req.getSession().setAttribute("user", user.get());
            return ":redirect";
        } else {
            req.setAttribute("message", ContentManager.getProperty("login.wrongLoginData", (String) req.getSession().getAttribute("lang")));
            return "/WEB-INF/view/login.jsp";
        }
    }
}
