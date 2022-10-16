package com.taxiservice.models.services;

import com.taxiservice.models.dao.BillDao;
import com.taxiservice.models.dao.DaoFactoryAbst;
import com.taxiservice.models.entities.Bill;

import java.util.Optional;

public class BillServiceImpl implements BillService {

    private DaoFactoryAbst daoFactoryAbst = DaoFactoryAbst.getInstance();

    @Override
    public boolean settleUp(Bill bill){
        try(BillDao dao = daoFactoryAbst.createBillDao()){
            return dao.settleUp(bill);
        }
    }
    @Override
    public Optional<Bill> getById(long id){
        try(BillDao dao = daoFactoryAbst.createBillDao()){
            return Optional.ofNullable(dao.findById(id));
        }
    }
}
