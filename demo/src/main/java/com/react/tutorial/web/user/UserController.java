package com.react.tutorial.web.user;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.react.tutorial.security.SHA256Util;
import com.react.tutorial.service.domain.User;
import com.react.tutorial.service.user.UserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/api/auth")
public class UserController {
	@Value("${JWT_SECRET}")
	private String jwtSecret;
	
	@Value("${TOKEN_NAME}")
	private String tokenName;
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;

	public UserController() {
		System.out.println(this.getClass());
	}

	@PostMapping("/login")
	public User login(@RequestBody User user, HttpServletResponse response) throws Exception {
		
		User loginUser = userService.authenticate(user);
		
		if(loginUser == null) {
			response.sendError(401);
		}else {
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.add(Calendar.DATE, 7);
			String jwt = Jwts.builder()
					.setSubject("user/"+loginUser.getUserId())
					.setExpiration(cal.getTime())
					.setNotBefore(new Date())
					.addClaims(loginUser.toMap())
					.signWith(
						    SignatureAlgorithm.HS256,
						    jwtSecret.getBytes("UTF-8")
						  )
					.compact();
			
			Cookie accessToken = new Cookie(tokenName, jwt);
			accessToken.setMaxAge(-1);
			accessToken.setPath("/");
			response.addCookie(accessToken);
		}
		
		return loginUser; 
	}
	
	@RequestMapping("/check")
	public User check(@CookieValue(name = "${TOKEN_NAME}") Cookie accessToken, HttpServletResponse response) throws Exception {
		String jwt = accessToken.getValue();

		if (jwt == null) {
			response.sendError(401);
		}
		
		User user = new User();
		
		try {
			Claims claims = Jwts.parser()
			  .setSigningKey(jwtSecret.getBytes("UTF-8"))
			  .parseClaimsJws(jwt).getBody();	
			
			user.setUserNo(Integer.parseInt(claims.get("userNo").toString()));
			user.setUserId(claims.get("userId").toString());
			user.setUsername(claims.get("username").toString());
			SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd HH:mm");
			user.setRegDate(df.parse(claims.get("regDate").toString()));
		}catch(Exception e) {
			//유효시간 지났을 경우
			response.sendError(401);
		}
		
		return user;
	}
	
	@RequestMapping("/logout")
	public void logout(HttpServletResponse response) {
		Cookie ck = new Cookie(tokenName, null);
		ck.setMaxAge(0);
		response.addCookie(ck);
	}

	@PostMapping("/register")
	public User addUser(@RequestBody User user, HttpServletResponse response) throws UnsupportedEncodingException {
		userService.addUser(user);
		user.setRegDate(new Date());
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, 7);
		String jwt = Jwts.builder()
				.setSubject("user/"+user.getUserId())
				.setExpiration(cal.getTime())
				.setNotBefore(new Date())
				.addClaims(user.toMap())
				.signWith(
					    SignatureAlgorithm.HS256,
					    jwtSecret.getBytes("UTF-8")
					  )
				.compact();
		
		Cookie accessToken = new Cookie(tokenName, jwt);
		accessToken.setMaxAge(-1);
		response.addCookie(accessToken);
		
		return user;
	}
	
	@RequestMapping("/updateUser")
	public User updateUser(@RequestBody User user) {
//		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		userService.updateUser(user);
		return user;
	}

	@RequestMapping("/deleteUser")
	public User deleteUser(@ModelAttribute User user) {
		userService.deleteUser(user);
		return user;
	}
	
	@RequestMapping("/userValidationCheck")
	public boolean userValidationCheck(@RequestBody JSONObject jsonObject) {
		return userService.userValidationCheck(jsonObject.get("userId").toString());
	}
}
