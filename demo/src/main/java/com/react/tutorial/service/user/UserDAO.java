package com.react.tutorial.service.user;

import com.react.tutorial.service.domain.User;

public interface UserDAO {
	public int addUser(User user);
	
	public String getSalt(String userId);

	public int updateUser(User user);

	public int deleteUser(User user);

	public User authenticate(User user);

	public User userValidationCheck(String userId);
}
