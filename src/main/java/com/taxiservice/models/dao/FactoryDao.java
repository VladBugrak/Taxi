package com.taxiservice.models.dao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class FactoryDao extends DaoFactoryAbst {

    private DataSource dataSource = ConnectionPoolHolder.getDataSource();

    @Override
    public UserDao createUserDao() {
        return new UserDaoImpl(getConnection());
    }
    @Override
    public RouteDao createRouteDao() {
        return new RouteDaoImpl(getConnection());
    }
    @Override
    public TariffDao createTariffDao() {
        return new TariffDaoImpl(getConnection());
    }
    @Override
    public OrderDao createOrderDao() {
        return new OrderDaoImpl(getConnection());
    }
    @Override
    public AvailableOptionDao createOptionDao() {
        return new AvailableOptionDaoImpl(getConnection());
    }
    @Override
    public BillDao createBillDao() {
        return new BillDaoImpl(getConnection());
    }


    private Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
