package com.zmrx.oauth.repo;

import java.util.List;

import com.zmrx.oauth.domain.User;
import org.hibernate.*;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	public int save(User u) {
		return (Integer) sessionFactory.getCurrentSession().save(u);
	}

	public List<User> findAll() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
		return criteria.list();
	}
	public List<User[]> findByCustomerSQL(String sql) {
		Session s = null;
		Transaction tx = null;
		try{
			s = sessionFactory.getCurrentSession();
			Query q = s.createSQLQuery(sql).addEntity(User.class);
			List<User[]> list = q.list();
			return list;
		}catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

}
