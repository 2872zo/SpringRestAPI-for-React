package com.react.tutorial.service.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Post {
	private int postNo;
	private String title;
	private String body;
	private String tags;
	private Date publishedDate;
	private Date updatedDate;
	private User user;
}
