package com.react.tutorial.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.react.tutorial.service.domain.User;
import com.react.tutorial.service.user.UserService;

@RestController
@RequestMapping("/api/auth")
public class UserController {
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;

	public UserController() {
		System.out.println(this.getClass());
	}

	@RequestMapping("/register")
	public User addUser(@RequestBody User user) {
		userService.addUser(user);
		return user;
	}
	
	@RequestMapping("/login")
	public User login(@RequestBody User user) {
		return userService.getUser(user);
	}
	
	@RequestMapping("/updateUser")
	public User updateUser(@RequestBody User user) {
		userService.updateUser(user);
		return user;
	}
	
	@RequestMapping("/deleteUser")
	public User deleteUser(@ModelAttribute User user) {
		userService.deleteUser(user);
		return user;
	}
}
