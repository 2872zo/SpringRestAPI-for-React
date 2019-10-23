package com.react.tutorial.service.post.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.react.tutorial.service.domain.Post;
import com.react.tutorial.service.domain.Search;
import com.react.tutorial.service.post.PostDAO;
import com.react.tutorial.service.post.PostService;

@Service
@Transactional
public class PostServiceImpl implements PostService {
	@Autowired
	@Qualifier("postDAOImpl")
	private PostDAO postDAO; 
	
	@Override
	public List<Post> getPosts(Search search) {
		return postDAO.getPosts(search);
	}

	@Override
	public Post getPost(int postNo) {
		return postDAO.getPost(postNo);
	}

	@Override
	public int addPost(Post post) {
		return postDAO.addPost(post);
	}

	@Override
	public int updatePost(Post post) {
		return postDAO.updatePost(post);
	}

	@Override
	public int deletePost(int postNo) {
		return postDAO.deletePost(postNo);
	}

}
