package com.taxiservice.controllers.listeners;

import com.taxiservice.models.entities.User;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
    }
    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

        Map<String, HttpSession> loggedUsers = (ConcurrentHashMap<String, HttpSession>) httpSessionEvent.getSession().getServletContext().getAttribute("loggedUsers");

        User user = (User) httpSessionEvent.getSession().getAttribute("user");

        loggedUsers.remove(user.getLogin());

        httpSessionEvent.getSession().getServletContext().setAttribute("loggedUsers", loggedUsers);
    }
}
