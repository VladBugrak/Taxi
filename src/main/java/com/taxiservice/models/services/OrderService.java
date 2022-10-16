package com.taxiservice.models.services;

import com.taxiservice.models.entities.Order;

import java.util.List;

public interface OrderService {

    Order create(Order order);

    List<Order> findByUserId(long idUser);
}
