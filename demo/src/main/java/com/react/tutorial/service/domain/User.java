package com.react.tutorial.service.domain;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class User {
	private int userNo;
	private String userId;
	private String username;
	private String password;
	private Date regDate;
	private String salt;

	public Map<String, Object> toMap(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("userNo", String.valueOf(userNo));
		map.put("userId", userId);
		map.put("username", username);
		map.put("regDate", df.format(regDate));
		
		return map;
	}
}
