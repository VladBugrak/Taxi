package com.taxiservice.controllers.commands.actions;

import com.taxiservice.controllers.commands.Command;
import com.taxiservice.models.entities.User;
import com.taxiservice.models.services.OrderService;
import com.taxiservice.models.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Optional;

public class UserCommand implements Command {

    private UserService userService;
    private OrderService orderService;

    public UserCommand(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        User user = (User) request.getSession().getAttribute("user");

        Optional<User> optionalUser = userService.getById(user.getId());

        if(optionalUser.isPresent()){
            user = optionalUser.get();
            user.setOrders(orderService.findByUserId(optionalUser.get().getId()));
        }

        request.setAttribute("userFull", user);

        return "/WEB-INF/view/user/userMenu.jsp";
    }
}
