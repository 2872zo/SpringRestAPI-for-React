package com.react.tutorial.service.user;

import com.react.tutorial.service.domain.User;

public interface UserService {
	public int addUser(User user);
	
	public String getSalt(String userId);
	
	public User authenticate(User user);
	
	public int updateUser(User user);
	
	public int deleteUser(User user);

	public boolean userValidationCheck(String userId);

}
