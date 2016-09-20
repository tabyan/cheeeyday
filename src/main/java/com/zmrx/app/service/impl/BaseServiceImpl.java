package com.zmrx.app.service.impl;

import com.zmrx.app.dao.BaseDao;
import com.zmrx.app.service.BaseService;
import com.zmrx.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by tabyan on 16-8-16.
 */
public class BaseServiceImpl<T> implements BaseService<T> {
    @Autowired
    public BaseDao<T> baseDao;

    private Class<T> t;

    public BaseServiceImpl(){
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        t = (Class<T>) params[0];
    }

    public List<T> findAll(){
        return baseDao.findAll(t);
    }

    public PageResult<T> findAll(int page,int pageSize, T Model) {
        PageResult<T> pr = baseDao.findAll(t,GetPR(page,pageSize),Model);
        return pr;
    }

    public PageResult<T> findAll(int page,int pageSize, T Model, boolean desc, String orderField) {
        PageResult<T> pr = baseDao.findAll(t,GetPR(page,pageSize),Model,desc,orderField);
        return pr;
    }

    public boolean save(T t) {
        return baseDao.save(t);
    }

    public boolean update(T t) {
        return baseDao.update(t);
    }

    public boolean delete(T t) {
        return baseDao.delete(t);
    }

    public boolean delete(int id) {
        return baseDao.delete(id,t);
    }

    public T getById(int id) {
        return (T)baseDao.getById(id,t);
    }

    public List<T> findByDataSQL(String sql) {
        return baseDao.findByDataSQL(sql,t);
    }

    public List<Object[]> findByCustomerSQL(String sql) {
        return baseDao.findByCustomerSQL(sql);
    }


    private PageResult<T> GetPR(int page,int pageSize){
        PageResult<T> pr = new PageResult<T>();
        pr.setPage(page);
        pr.setPageSize(pageSize);
        return pr;
    }
}
