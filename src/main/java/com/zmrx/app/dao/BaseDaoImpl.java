package com.zmrx.app.dao;

import com.zmrx.utils.PageResult;
import org.hibernate.*;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by tabyan on 16-8-16.
 */

@Repository("baseDao")
public class BaseDaoImpl<T> implements BaseDao<T>{


    @Autowired
    public SessionFactory sessionFactory;

    public Session getSession(){
        return sessionFactory.openSession();
    }

    public PageResult<T> findAll(Class<T> t, PageResult<T> pr, T Model) {
        return findAll(t,pr,Model,false,"");
    }

    public PageResult<T> findAll(Class<T> t, PageResult<T> pr,T Model,boolean desc, String orderField) {
        Criteria criteria;
        Session s = getSession();
        try{
            criteria = s.createCriteria(Class.forName(t.getName()));
            criteria = GetExpression(Model,criteria);
            pr = ExeCriteria(criteria,pr,desc,orderField);
        }catch (HibernateException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }finally {
            s.close();
        }
        return pr;
    }

    public List<T> findAll(Class<T> t) {
        Session s = getSession();
        System.out.print(s);
        Criteria c = s.createCriteria(t);
        List<T> list = c.list();
        s.close();
        return list;
    }

    public boolean save(T t) {
        boolean rst = false;
        Session s = null;
        try{
            s = getSession();
            s.save(s.merge(t));
            rst = true;
        }catch (HibernateException e){
            e.printStackTrace();
        }finally {
            s.close();
        }
        return rst;
    }

    public boolean update(T t) {
        boolean rst = false;
        Session s = null;
        Transaction tx = null;
        try{
            s = getSession();
            tx = s.beginTransaction();
            s.update(s.merge(t));
            tx.commit();
            rst = true;
        }catch (HibernateException e){
            e.printStackTrace();
        }finally {
            s.close();
        }
        return rst;
    }

    public boolean delete(T t) {
        boolean rst = false;
        Session s = null;
        try{
            s = getSession();
            s.delete(s.merge(t));
            rst = true;
        }catch (HibernateException e){
            e.printStackTrace();
        }finally {
            s.close();
        }
        return rst;
    }

    public boolean delete(Integer id, Class<T> t) {
        boolean rst = false;
        Session s = null;
        try{
            s = getSession();
            Query query = s.createQuery("delete "+t.getSimpleName()+" where objectid = ?");
            query.setInteger(0,id);
            query.executeUpdate();
            rst = true;
        }catch (HibernateException e){
            e.printStackTrace();
        }finally {
            s.close();
        }
        return rst;
    }

    public T getById(Integer id, Class<T> t) {
        Session s = null;
        try{
            s = getSession();
            Criteria c = s.createCriteria(t);
            c.add(Expression.eq("id",id));
            List<T> list = c.list();
            if (!list.isEmpty()){
                return (T)list.get(0);
            }else {
                return null;
            }
        }catch (HibernateException e){
            e.printStackTrace();
            return null;
        }finally {
            s.close();
        }
    }

    public List<Object[]> findByCustomerSQL(String sql) {
        Session s = null;
        try{
            s = getSession();
            Query query = s.createQuery(sql);
            List<Object[]> list = query.list();
            return list;
        }catch (HibernateException e){
            e.printStackTrace();
            return null;
        }finally {
            s.close();
        }
    }
    public List<T> findByDataSQL(String sql,Class<T> t) {
        Session s = null;
        try{
            s = getSession();
            Query query = s.createSQLQuery(sql).addEntity(t);
            List<T> list = query.list();
            return list;
        }catch (HibernateException e){
            e.printStackTrace();
            return null;
        }finally {
            s.close();
        }
    }

    private Criteria GetExpression(T Model,Criteria criteria){
        Class cla = (Class)Model.getClass();
        Field[] fs = cla.getFields();
        for (int i = 0;i < fs.length;i++){
            Field f = fs[i];
            f.setAccessible(true);
            Object val;
            try{
                val = f.get(Model);
                String type = f.getType().toString();
                if (type.endsWith("String")){
                    if (val != null && !val.toString().isEmpty()){
                        criteria.add(Expression.like(f.getName(),"%"+val+"%"));
                    }
                }else if (type.endsWith("int") || type.endsWith("Integer")){
                    if (val != null && !val.toString().equals("0")){
                        String fieldname = f.getName();
                        if (fieldname.endsWith("id")){
                            String temp = fieldname.substring(0,fieldname.length()-2);
                            try{
                                Field ff = cla.getField(temp);
                                criteria.add(Expression.eq(temp+".objectid",val));
                            }catch (NoSuchFieldException e){
                                e.printStackTrace();
                            }
                        }
                    }else {
                        criteria.add(Expression.eq(f.getName(),val));
                    }
                }else if (type.endsWith("Date")){
                    if (val != null){
                        DateFormat df1 = DateFormat.getDateInstance();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
                        String current = df1.format(val);
                        criteria.add(Expression.ge(f.getName(),sdf.parse(current)));
                        Date dd = sdf.parse(current);
                        Calendar cl = Calendar.getInstance();
                        cl.set(dd.getYear()+1900,dd.getMonth(),dd.getDate());
                        cl.add(Calendar.DATE,1);
                        String next = df1.format(cl.getTime());
                        criteria.add(Expression.lt(f.getName(),sdf.parse(next)));
                    }
                }else {

                }
            }catch (IllegalArgumentException e){
                e.printStackTrace();
            }catch (IllegalAccessException e){
                e.printStackTrace();
            }catch (ParseException e){
                e.printStackTrace();
            }
        }
        return criteria;
    }

    private PageResult<T> ExeCriteria(Criteria criteria,PageResult<T> pr,boolean desc,String orderField){
        int rowCount = Integer.parseInt(criteria.setProjection(Projections.rowCount()).uniqueResult().toString());
        criteria.setProjection(null);
        if (pr.getPage() > 0){
            criteria.setFirstResult((pr.getPage() -1)*pr.getPageSize());
            criteria.setMaxResults(pr.getPageSize());
        }
        if (orderField != null && !orderField.endsWith("")){
            if (desc){
                for (String s:orderField.split(",")){
                    criteria.addOrder(Order.desc(s));
                }
            }else {
                for (String s:orderField.split(",")){
                    criteria.addOrder(Order.asc(s));
                }
            }
        }

        List<T> result = criteria.list();
        pr.setTotal(rowCount);
        pr.setResult(result);
        return totalPage(pr);
    }

    private PageResult<T> totalPage(PageResult<T> pr){
        if (pr.getPageSize() > pr.getTotal()){
            pr.setTotalPage(1);
        }else if (pr.getTotal() % pr.getPageSize() > 0){
            pr.setTotalPage(pr.getTotal() / pr.getPageSize() + 1);
        }else {
            pr.setTotalPage(pr.getTotal() / pr.getPageSize());
        }
        return pr;
    }
}
