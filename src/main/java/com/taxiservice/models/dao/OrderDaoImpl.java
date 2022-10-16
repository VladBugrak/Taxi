package com.taxiservice.models.dao;

import com.taxiservice.models.dao.mapper.*;
import com.taxiservice.models.entities.*;
import com.taxiservice.utils.BundleManagers.SqlQueryManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDaoImpl implements OrderDao {

    private Connection connection;

    OrderDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Order create(Order order) {

        try(PreparedStatement stOrder = connection.prepareStatement(SqlQueryManager.getProperty("order.create"), Statement.RETURN_GENERATED_KEYS);
            PreparedStatement stBill = connection.prepareStatement(SqlQueryManager.getProperty("bill.create"), Statement.RETURN_GENERATED_KEYS)){

            connection.setAutoCommit(false);

            stOrder.setString(1, order.getType().name());
            stOrder.setInt(2, order.getWeightGr());
            stOrder.setDate(3, Date.valueOf(order.getArrivalDate()));
            stOrder.setLong(4, order.getAvailableOption().getId());
            stOrder.setLong(5, order.getUser().getId());

            stOrder.executeUpdate();

            ResultSet rs = stOrder.getGeneratedKeys();

            if(rs.next()){
                order.setId(rs.getLong(1));
            }
            else {
                connection.rollback();
                throw new RuntimeException("order insert trouble");
            }

            stBill.setLong(1, order.getBill().getTotal());
            stBill.setBoolean(2, order.getBill().isPaid());
            stBill.setLong(3, order.getUser().getId());
            stBill.setLong(4, order.getId());

            stBill.executeUpdate();

            ResultSet billRs = stBill.getGeneratedKeys();

            if(billRs.next()){
                order.getBill().setId(rs.getLong(1));
            }
            else {
                connection.rollback();
                throw new RuntimeException("bill insert trouble");
            }

            connection.commit();

            return order;
        }
        catch (SQLException e){
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            throw new RuntimeException(e);
        }

    }

    @Override
    public Order findById(long id) {

        OrderMapper mapper = new OrderMapper();

        try (PreparedStatement ps = connection.prepareStatement(SqlQueryManager.getProperty("order.findById"))) {

            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            if(rs.next())
                return mapper.extractFromResultSet(rs);
            else
                return null;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Order> findAll() {

        List<Order> orderList = new ArrayList<>();

        OrderMapper orderMapper = new OrderMapper();
        BillMapper billMapper = new BillMapper();
        AvailableOptionMapper availableOptionMapper = new AvailableOptionMapper();
        RouteMapper routeMapper = new RouteMapper();
        TariffMapper tariffMapper = new TariffMapper();

        try (PreparedStatement ps = connection.prepareStatement(SqlQueryManager.getProperty("order.findAll"))) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Order order = orderMapper.extractFromResultSet(rs);

                AvailableOption availableOption = availableOptionMapper.extractFromResultSet(rs);

                availableOption.setTariff(tariffMapper.extractFromResultSet(rs));
                availableOption.setRoute(routeMapper.extractFromResultSet(rs));

                order.setAvailableOption(availableOption);
                order.setBill(billMapper.extractFromResultSet(rs));

                orderList.add(order);

            }

            return orderList;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(Order order) {

        try(PreparedStatement ps = connection.prepareStatement(SqlQueryManager.getProperty("order.update"))){

            ps.setString(1, order.getType().name());
            ps.setInt(2, order.getWeightGr());
            ps.setDate(3, Date.valueOf(order.getArrivalDate()));

            return ps.executeUpdate()>0;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean delete(long id) {

        try(PreparedStatement ps = connection.prepareStatement(SqlQueryManager.getProperty("order.delete"))){

            ps.setLong(1, id);

            return ps.executeUpdate()>0;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Order> findByUserId(long idUser) {

        Map<Long, Order> orderMap = new HashMap<>();

        OrderMapper orderMapper = new OrderMapper();
        BillMapper billMapper = new BillMapper();
        AvailableOptionMapper availableOptionMapper = new AvailableOptionMapper();
        RouteMapper routeMapper = new RouteMapper();
        TariffMapper tariffMapper = new TariffMapper();

        try (PreparedStatement ps = connection.prepareStatement(SqlQueryManager.getProperty("order.findByUserId"))) {

            ps.setLong(1, idUser);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Order order = orderMapper.extractFromResultSet(rs);

                Bill bill = billMapper.extractFromResultSet(rs);
                AvailableOption availableOption = availableOptionMapper.extractFromResultSet(rs);
                Route route = routeMapper.extractFromResultSet(rs);
                Tariff tariff = tariffMapper.extractFromResultSet(rs);

                availableOption.setTariff(tariff);
                availableOption.setRoute(route);
                bill.setOrder(order);
                order.setAvailableOption(availableOption);
                order.setBill(bill);

                orderMapper.makeUnique(orderMap, order);

            }

            return new ArrayList<>(orderMap.values());

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


}
