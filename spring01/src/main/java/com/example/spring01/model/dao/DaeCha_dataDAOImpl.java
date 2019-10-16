package com.example.spring01.model.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.spring01.model.dto.DaeCha_dataDTO;



@Repository
public class DaeCha_dataDAOImpl implements DaeCha_dataDAO {

	@Inject
	SqlSession sqlSession;
	@Override
	public void insert(DaeCha_dataDTO dto) {
		System.out.println("DAOImpl="+dto);
		sqlSession.insert("daecha.insert",dto);
	}
	@Override
	public String check(String num) {
		
		return sqlSession.selectOne("daecha.check",num);
	}

}
