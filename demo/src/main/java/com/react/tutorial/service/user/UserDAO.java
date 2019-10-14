package com.react.tutorial.service.user;

import com.react.tutorial.service.domain.User;

public interface UserDAO {
	public int addUser(User user);

	public User getUser(User user);

	public int updateUser(User user);

	public int deleteUser(User user);
}
