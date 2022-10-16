package com.taxiservice.controllers.commands.actions;

import com.taxiservice.controllers.commands.Command;
import com.taxiservice.models.entities.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class LogoutCommand implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        Map<String, HttpSession> loggedUsers = (ConcurrentHashMap<String, HttpSession>) req.getServletContext().getAttribute("loggedUsers");

        Optional<User> optionalUser = Optional.ofNullable( (User) req.getSession().getAttribute("user") );

        optionalUser.ifPresent(user->{
            loggedUsers.remove(user.getLogin());
            req.getServletContext().setAttribute("loggedUsers", loggedUsers);
            req.getSession().removeAttribute("user");
        } );

        return ":redirect";
    }
}
