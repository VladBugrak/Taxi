package com.taxiservice.models.services;

import com.taxiservice.models.dao.DaoFactoryAbst;
import com.taxiservice.models.dao.OrderDao;
import com.taxiservice.models.entities.Order;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    private DaoFactoryAbst daoFactoryAbst = DaoFactoryAbst.getInstance();

    @Override
    public Order create(Order order) {
        try (OrderDao dao = daoFactoryAbst.createOrderDao()) {
            return dao.create(order);
        }
    }
    @Override
    public List<Order> findByUserId(long idUser) {
        try (OrderDao dao = daoFactoryAbst.createOrderDao()) {
            return dao.findByUserId(idUser);
        }
    }
}
