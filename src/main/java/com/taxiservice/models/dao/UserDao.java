package com.taxiservice.models.dao;

import com.taxiservice.models.entities.User;

public interface UserDao extends GenericDao<User> {

    User findByLoginPassword(String name, String password);

    boolean refill(User user, Long payment);
}
