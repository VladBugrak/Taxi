package com.taxiservice.models.services;

import com.taxiservice.models.entities.Bill;

import java.util.Optional;

public interface BillService {
    boolean settleUp(Bill bill);

    Optional<Bill> getById(long id);
}
