package com.example.spring01.model.dao;

import java.util.Map;

public interface UserDAO {
	public int insertUser(Map<String, String> map);

	public Map<String, Object> selectUser(String userid);
}
