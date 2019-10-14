package com.react.tutorial.service.domain;

import java.util.Date;

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

}
