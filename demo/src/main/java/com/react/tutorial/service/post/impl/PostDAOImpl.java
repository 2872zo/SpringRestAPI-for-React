package com.react.tutorial.service.post.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.react.tutorial.service.domain.Post;
import com.react.tutorial.service.domain.Search;
import com.react.tutorial.service.post.PostDAO;

@Repository
public class PostDAOImpl implements PostDAO {
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;

	@Override
	public List<Post> getPosts(Search search) {
		return sqlSession.selectList("PostMapper.getPosts", search);
	}

	@Override
	public Post getPost(int postNo) {
		return sqlSession.selectOne("PostMapper.getPost", postNo);
	}

	@Override
	public int addPost(Post post) {
		return sqlSession.insert("PostMapper.addPost", post);
	}

	@Override
	public int updatePost(Post post) {
		return sqlSession.update("PostMapper.updatePost", post);
	}

	@Override
	public int deletePost(int postNo) {
		return sqlSession.delete("PostMapper.deletePost", postNo);
	}

}
