package com.taxiservice.models.dao;

import com.taxiservice.controllers.exceptions.NotEnoughMoneyException;
import com.taxiservice.controllers.exceptions.SettleUpDuplicationException;
import com.taxiservice.models.dao.mapper.BillMapper;
import com.taxiservice.models.dao.mapper.UserMapper;
import com.taxiservice.models.entities.Bill;
import com.taxiservice.utils.BundleManagers.SqlQueryManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillDaoImpl implements BillDao {

    private Connection connection;

    BillDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Bill create(Bill bill) {

        try(PreparedStatement ps = connection.prepareStatement(SqlQueryManager.getProperty("bill.create"), Statement.RETURN_GENERATED_KEYS)){

            ps.setLong(1, bill.getTotal());
            ps.setBoolean(2, bill.isPaid());
            ps.setLong(3, bill.getUser().getId());
            ps.setLong(4, bill.getOrder().getId());

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();

            if(rs.next()){
                bill.setId(rs.getLong(1));
                return bill;
            }
            else
                return null;

        }
        catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    @Override
    public Bill findById(long id) {

        BillMapper billMapper = new BillMapper();
        UserMapper userMapper = new UserMapper();

        try (PreparedStatement ps = connection.prepareStatement(SqlQueryManager.getProperty("bill.findById"))){

            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {

                Bill bill = billMapper.extractFromResultSet(rs);
                bill.setUser(userMapper.extractFromResultSet(rs));

                return bill;
            }
            else
                return null;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Bill> findAll() {

        List<Bill> billList = new ArrayList<>();

        BillMapper billMapper = new BillMapper();
        UserMapper userMapper = new UserMapper();

        try (PreparedStatement ps = connection.prepareStatement(SqlQueryManager.getProperty("bill.findAll"))){

            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                Bill bill = billMapper.extractFromResultSet(rs);
                bill.setUser(userMapper.extractFromResultSet(rs));

                billList.add(bill);
            }

            return billList;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(Bill bill) {

        try(PreparedStatement ps = connection.prepareStatement(SqlQueryManager.getProperty("bill.update"))){

            ps.setLong(1, bill.getTotal());
            ps.setBoolean(2, bill.isPaid());
            ps.setLong(3, bill.getId());

            return ps.executeUpdate()>0;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean delete(long id) {

        try(PreparedStatement ps = connection.prepareStatement(SqlQueryManager.getProperty("bill.delete"))){

            ps.setLong(1, id);

            return ps.executeUpdate()>0;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean settleUp(Bill bill){

        long chargeSum = bill.getTotal();
        long userId = bill.getUser().getId();

        try( PreparedStatement stUser = connection.prepareStatement(SqlQueryManager.getProperty("user.chargeOff"));
             PreparedStatement stUserCheck = connection.prepareStatement(SqlQueryManager.getProperty("user.findById"));
             PreparedStatement stBillCheck = connection.prepareStatement(SqlQueryManager.getProperty("bill.checkIsPaid") );
             PreparedStatement stBill = connection.prepareStatement(SqlQueryManager.getProperty("bill.settleUp") ) ){

            connection.setAutoCommit(false);

            stUser.setLong(1, chargeSum);
            stUser.setLong(2, userId);
            stUser.executeUpdate();

            stUserCheck.setLong(1, userId);
            ResultSet rs = stUserCheck.executeQuery();

            stBillCheck.setLong(1, bill.getId());
            ResultSet resultSet = stBillCheck.executeQuery();

            stBill.setLong(1, bill.getId());
            stBill.executeUpdate();

            if(resultSet.next() && resultSet.getBoolean("is_paid")){

                connection.rollback();

                throw new SettleUpDuplicationException("bill is already paid");
            }

            if(rs.next() && rs.getLong("account_sum") <0){

                connection.rollback();

                throw new NotEnoughMoneyException("not enought money on account to settleUp bill");
            }

            else{
                connection.commit();

                return true;
            }

        }

        catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException esql) {
                throw new RuntimeException(esql);
            }
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}
