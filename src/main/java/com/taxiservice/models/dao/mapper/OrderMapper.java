package com.taxiservice.models.dao.mapper;

import com.taxiservice.models.entities.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class OrderMapper implements ObjectMapper<Order> {

    @Override
    public Order extractFromResultSet(ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setId(rs.getLong("id_order"));
        order.setWeightGr(rs.getInt("weight"));
        order.setType(Order.Type.valueOf(rs.getString("type") ) );
        order.setArrivalDate(rs.getDate("arrival_date").toLocalDate());
        return order;
    }

    @Override
    public Order makeUnique(Map<Long, Order> cache, Order order) {
        cache.putIfAbsent(order.getId(), order);
        return cache.get(order.getId());
    }
}
