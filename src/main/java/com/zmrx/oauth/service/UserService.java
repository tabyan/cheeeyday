package com.zmrx.oauth.service;

import com.zmrx.oauth.domain.User;

import java.util.List;


public interface UserService {
	public void save(User u);
	public void saveUsers(List<User> us);
	public List<User> getAllUsers();
	List<User[]> findByCustomerSQL(String sql);
}
