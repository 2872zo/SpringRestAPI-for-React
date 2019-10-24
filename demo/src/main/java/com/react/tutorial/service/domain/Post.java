package com.react.tutorial.service.domain;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
	private List<String> tags;
	private String tagsString;
	private Date publishedDate;
	private Date updatedDate;
	private User user;
	
//	public void setTags(String tags) {
//		this.tags = Arrays.asList(tags.split(","));
//	}
	
	public void setTags(List<String> tags) {
		this.tags = tags;
		tagsString = tags.toString().substring(1,tags.toString().length()-1).trim();
	}
	
	public void setTagsString(String tagsString) {
		this.tagsString = tagsString;
		tags =  Arrays.asList(tagsString.split(","));
	}
}
