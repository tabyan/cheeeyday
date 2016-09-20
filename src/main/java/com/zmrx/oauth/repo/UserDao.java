package com.zmrx.oauth.repo;

import com.zmrx.oauth.domain.User;

import java.util.List;


public interface UserDao {
	public int save(User u);
	public List<User> findAll();
	public List<User[]> findByCustomerSQL(String sql);
}
