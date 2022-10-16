package com.taxiservice.models.dao;

import com.taxiservice.models.entities.Order;

import java.util.List;

public interface OrderDao extends GenericDao<Order> {
    List<Order> findByUserId(long idUser);
}
