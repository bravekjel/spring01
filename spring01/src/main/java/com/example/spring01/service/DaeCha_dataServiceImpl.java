package com.example.spring01.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.example.spring01.model.dao.DaeCha_dataDAO;
import com.example.spring01.model.dto.DaeCha_dataDTO;
@Service
public class DaeCha_dataServiceImpl implements DaeCha_dataService {

	@Inject
	DaeCha_dataDAO daechaDao;
	@Override
	public void insert(DaeCha_dataDTO dto) {
		daechaDao.insert(dto);
	}

}
