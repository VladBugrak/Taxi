package com.taxiservice.models.dao.mapper;

import com.taxiservice.models.entities.Bill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class BillMapper implements ObjectMapper<Bill> {
    @Override
    public Bill extractFromResultSet(ResultSet rs) throws SQLException {
        Bill bill = new Bill();
        bill.setId(rs.getLong("id_bill"));
        bill.setPaid(rs.getBoolean("is_paid"));
        bill.setTotal(rs.getLong("total"));
        return bill;
    }

    @Override
    public Bill makeUnique(Map<Long, Bill> cache, Bill bill) {
        cache.putIfAbsent(bill.getId(), bill);
        return cache.get(bill.getId());
    }

}
