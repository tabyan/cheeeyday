package com.zmrx.app.dao;

import com.zmrx.utils.PageResult;

import java.util.List;

public interface BaseDao<T>{

    PageResult<T> findAll(Class<T> t, PageResult<T> pr, T Model);

    PageResult<T> findAll(Class<T> t, PageResult<T> pr, T Model, boolean desc, String orderField);



    List<T> findAll(Class<T> t);

    boolean save(T t);

    boolean update(T t);

    boolean delete(T t);

    boolean delete(Integer id, Class<T> t);

    T getById(Integer id, Class<T> t);

    List<Object[]> findByCustomerSQL(String sql);

    List<T> findByDataSQL(String sql, Class<T> t);
}