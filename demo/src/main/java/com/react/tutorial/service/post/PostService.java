package com.react.tutorial.service.post;

import java.util.List;

import com.react.tutorial.service.domain.Post;
import com.react.tutorial.service.domain.Search;

public interface PostService {
	// 가져올 page번호와 keyword
	public List<Post> getPosts(Search search);

	// 가져올 post 번호
	public Post getPost(int postNo);

	// 추가할 post 정보(user포함)
	public int addPost(Post post);

	// 업데이트할 post 정보(user포함)
	public int updatePost(Post post);

	// 삭제할 post번호와 user정보
	public int deletePost(int postNo);

}
