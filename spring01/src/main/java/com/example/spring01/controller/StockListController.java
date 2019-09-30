package com.example.spring01.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/stockList/*")
public class StockListController {


	
@RequestMapping("slist.do")
public String slist(HttpServletRequest request,Model model) {
	System.out.println("slist.do");
	return "stock/slist";
	
}
}
