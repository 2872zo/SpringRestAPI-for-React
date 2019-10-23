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
	public String getSalt(String userId) {
		return sqlSession.selectOne("UserMapper.getSalt", userId);
	}

	@Override
	public User authenticate(User user) {
		return sqlSession.selectOne("UserMapper.authenticate", user);
	}

	@Override
	public int updateUser(User user) {
		return sqlSession.insert("UserMapper.updateUser", user);
	}

	@Override
	public int deleteUser(User user) {
		return sqlSession.insert("UserMapper.deleteUser", user);
	}

	@Override
	public User userValidationCheck(String userId) {
		return sqlSession.selectOne("UserMapper.userValidationCheck", userId);
	}

}
