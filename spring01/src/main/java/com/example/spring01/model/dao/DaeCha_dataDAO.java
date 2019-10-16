package com.example.spring01.model.dao;

import com.example.spring01.model.dto.DaeCha_dataDTO;

public interface DaeCha_dataDAO {
// 대차 정보 담기 
public void insert(DaeCha_dataDTO dto);
public String check(String num);
}
