package com.taxiservice.models.dao;

import com.taxiservice.models.entities.Bill;

public interface BillDao extends GenericDao<Bill> {
    boolean settleUp(Bill bill);
}
