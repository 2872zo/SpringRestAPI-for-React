package com.react.tutorial.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.react.tutorial.security.SHA256Util;
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
		// 비밀번호 암호화
		user.setSalt(SHA256Util.generateSalt());
		user.setPassword(SHA256Util.getEncrypt(user.getPassword(), user.getSalt()));
		return userDAO.addUser(user);
	}

	@Override
	public User authenticate(User user) {
		user.setSalt(userDAO.getSalt(user.getUserId()));
		user.setPassword(SHA256Util.getEncrypt(user.getPassword(), user.getSalt()));
		return userDAO.authenticate(user);
	}

	@Override
	public String getSalt(String userId) {
		return userDAO.getSalt(userId);
	}

	@Override
	public int updateUser(User user) {
		return userDAO.updateUser(user);
	}

	@Override
	public int deleteUser(User user) {
		return userDAO.deleteUser(user);
	}

	@Override
	public boolean userValidationCheck(String userId) {
		return userDAO.userValidationCheck(userId) == null? true: false;
	}

}
