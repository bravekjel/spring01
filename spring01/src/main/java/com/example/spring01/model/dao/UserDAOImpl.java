package com.example.spring01.model.dao;

import java.util.Map;
import javax.inject.Inject;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO {
	@Inject
	SqlSession sqlSession;

	@Override
	public int insertUser(Map<String, String> map) {
		return sqlSession.insert("user.insertUser", map);
	}

	@Override
	public Map<String, Object> selectUser(String userid) {
		return sqlSession.selectOne("user.selectUser", userid);
	}
}