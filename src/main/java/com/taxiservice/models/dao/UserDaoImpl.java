package com.taxiservice.models.dao;

import com.taxiservice.controllers.exceptions.NotUniqUserException;
import com.taxiservice.models.dao.mapper.OrderMapper;
import com.taxiservice.models.dao.mapper.UserMapper;
import com.taxiservice.models.entities.Order;
import com.taxiservice.models.entities.User;
import com.taxiservice.utils.BundleManagers.SqlQueryManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDaoImpl implements UserDao {

    private Connection connection;

    UserDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User create(User user) {

        try(PreparedStatement ps = connection.prepareStatement(SqlQueryManager.getProperty("user.createUser"),
                Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, user.getLogin());
            ps.setString( 2, user.getPassword());
            ps.setString( 3, user.getFirstName());
            ps.setString( 4, user.getLastName());
            ps.setString( 5, user.getEmail());
            ps.setString( 6, user.getRole().name());

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();

            if(rs.next()){
                user.setId(rs.getLong(1));
                return user;
            }
            else
                return null;
        }
        catch (SQLIntegrityConstraintViolationException e){
            throw new NotUniqUserException("User with such name already exist");
        }
        catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    @Override
    public User findById(long id) {

        UserMapper mapper = new UserMapper();

        try (PreparedStatement ps = connection.prepareStatement(SqlQueryManager.getProperty("user.findById"))) {

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
    public List<User> findAll() {

        Map<Long, User> userMap = new HashMap<>();

        UserMapper userMapper = new UserMapper();
        OrderMapper orderMapper = new OrderMapper();

        try (Statement st = connection.createStatement()) {

            ResultSet rs = st.executeQuery(SqlQueryManager.getProperty("user.findAll"));

            while (rs.next()) {
                User user = userMapper
                        .extractFromResultSet(rs);
                Order order = orderMapper
                        .extractFromResultSet(rs);
                user = userMapper
                        .makeUnique(userMap, user);

                user.getOrders().add(order);
            }

            return new ArrayList<>(userMap.values());

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean update(User user) {

        try(PreparedStatement ps = connection.prepareStatement(SqlQueryManager.getProperty("user.update"))){

            ps.setString(1, user.getLogin());
            ps.setString( 2, user.getPassword());
            ps.setString( 3, user.getFirstName());
            ps.setString( 4, user.getLastName());
            ps.setString( 5, user.getEmail());
            ps.setString( 6, user.getRole().name());
            ps.setLong(7, user.getId());

            return ps.executeUpdate()>0;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(long id) {

        try(PreparedStatement ps = connection.prepareStatement(SqlQueryManager.getProperty("user.delete"))){

            ps.setLong(1, id);

            return ps.executeUpdate()>0;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close()  {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findByLoginPassword(String login, String password) {

        UserMapper mapper = new UserMapper();

        try(PreparedStatement ps = connection.prepareStatement(SqlQueryManager.getProperty("user.findByLoginPassword"))){

            ps.setString( 1, login);
            ps.setString( 2, password);

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
    public boolean refill(User user, Long payment){

        try(PreparedStatement ps = connection.prepareStatement(SqlQueryManager.getProperty("user.refill"))){

            ps.setLong(1, payment);
            ps.setLong(2, user.getId());

            return ps.executeUpdate()>0;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}

