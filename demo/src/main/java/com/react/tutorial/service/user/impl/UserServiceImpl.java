package com.react.tutorial.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.react.tutorial.service.domain.User;
import com.react.tutorial.service.user.UserDAO;
import com.react.tutorial.service.user.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	@Qualifier("userDAOImpl")
	private UserDAO userDAO;
	
	public UserServiceImpl() {
		System.out.println(this.getClass());
	}

	@Override
	public int addUser(User user) {
		return userDAO.addUser(user);
	}

	@Override
	public User getUser(User user) {
		return userDAO.getUser(user);
	}

	@Override
	public int updateUser(User user) {
		return userDAO.updateUser(user);
	}

	@Override
	public int deleteUser(User user) {
		return userDAO.deleteUser(user);
	}
}
