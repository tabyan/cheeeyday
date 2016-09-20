package com.zmrx.app.service;

import com.zmrx.utils.PageResult;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by tabyan on 16-8-16.
 */
public interface BaseService<T> {
    List<T> findAll();

    PageResult<T> findAll(int page, int pageSize, T Model);

    PageResult<T> findAll(int page, int pageSize, T Model, boolean desc, String orderField);

    boolean save(T t);

    boolean update(T t);

    boolean delete(T t);

    boolean delete(int id);

    T getById(int id);

    List<T> findByDataSQL(String sql);

    List<Object[]> findByCustomerSQL(String sql);
}
