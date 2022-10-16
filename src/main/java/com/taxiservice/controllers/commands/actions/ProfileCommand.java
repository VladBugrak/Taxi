package com.taxiservice.controllers.commands.actions;

import com.taxiservice.controllers.commands.Command;
import com.taxiservice.models.entities.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Optional;

public class ProfileCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        User user = Optional.ofNullable(((User) request.getSession().getAttribute("user"))).orElse(new User());

        return user.getRole().getPath();
    }
}
