package com.taxiservice.models.dao;

import java.util.List;

public interface GenericDao<T> extends AutoCloseable {
    T create (T entity);
    T findById(long id);
    List<T> findAll();
    boolean update(T entity);
    boolean delete(long id);
    void close();
}
