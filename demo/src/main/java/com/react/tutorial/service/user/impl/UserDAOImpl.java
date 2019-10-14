package com.react.tutorial.service.user.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.react.tutorial.service.domain.User;
import com.react.tutorial.service.user.UserDAO;

@Repository
public class UserDAOImpl implements UserDAO {
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;

	public UserDAOImpl() {
		System.out.println(this.getClass());
	}
	
	@Override
	public int addUser(User user) {
		return sqlSession.insert("UserMapper.addUser", user);
	}

	@Override
	public User getUser(User user) {
		return sqlSession.selectOne("UserMapper.getUser", user);
	}

	@Override
	public int updateUser(User user) {
		return sqlSession.insert("UserMapper.updateUser", user);
	}

	@Override
	public int deleteUser(User user) {
		return sqlSession.insert("UserMapper.deleteUser", user);
	}

}
