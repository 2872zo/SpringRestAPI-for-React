package com.react.tutorial.service.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Search {
	private int pageNum;
	private String keyword;
}
