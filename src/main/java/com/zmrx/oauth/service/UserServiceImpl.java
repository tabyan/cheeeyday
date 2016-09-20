package com.zmrx.oauth.service;

import java.util.List;

import javax.transaction.Transactional;

import com.zmrx.oauth.domain.User;
import com.zmrx.oauth.repo.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	public void saveUsers(List<User> us) {
		for (User u : us) {
			userDao.save(u);
		}
	}

	public List<User> getAllUsers() {
		return userDao.findAll();
	}

	public List<User[]> findByCustomerSQL(String sql) {
		return userDao.findByCustomerSQL(sql);
	}

	public void save(User u) {
		userDao.save(u);
	}

}
