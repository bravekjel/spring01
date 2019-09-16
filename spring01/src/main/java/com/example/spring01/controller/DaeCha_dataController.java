package com.example.spring01.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.spring01.model.dto.DaeCha_dataDTO;
import com.example.spring01.service.DaeCha_dataService;

@Controller
@RequestMapping("/daecha/*")
public class DaeCha_dataController {
	@Inject
	DaeCha_dataService daechaService;
	
	@RequestMapping("insert.do")
	public String insert(@ModelAttribute DaeCha_dataDTO dto, HttpServletRequest request) {
		System.out.println("insert.do");
		String s_num = request.getParameter("num");
		dto.setS_num(s_num);
		daechaService.insert(dto);
		return "home";
	}

}
