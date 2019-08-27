package com.example.spring01.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/search_stock/*")
public class SearchController {

	@RequestMapping("disInfo.do")
	public String disInfo(HttpServletRequest request) {
		String num = request.getParameter("num");
		request.setAttribute("key", num);
		System.out.println("num="+num);
		return "/include/search_stock/disInfo";
	}
	@RequestMapping("financialInfo.do")
	public String financialInfo(HttpServletRequest request) {
		String num = request.getParameter("num");
		request.setAttribute("key", num);
		System.out.println("num="+num);
		return "/include/search_stock/financialInfo";
	}

	@RequestMapping("stockInfo.do{num}")
	public String stockInfo(HttpServletRequest request) {
		String num = request.getParameter("num");
		request.setAttribute("key", num);
		System.out.println("number전달값="+num);
		return "/include/search_stock/stockInfo";
		
	}

}
