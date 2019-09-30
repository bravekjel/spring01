package com.example.spring01.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.spring01.model.dao.UserDAO;

@Controller
public class UserController {
	@Inject
	BCryptPasswordEncoder passwordEncoder;
	@Inject
	private UserDAO userDao;

	@RequestMapping("/")
	public String home(Locale locale, Model model,HttpServletRequest req,HttpSession session) {
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String name= (String) req.getAttribute("name");
	
		System.out.println("name="+name);
		session.setAttribute("writer", name);
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}

	@RequestMapping("/user/login.do")
	public String login() {
		return "user/login";
	}

	@RequestMapping("/user/join.do")
	public String join() {
		return "user/join";
	}

	@RequestMapping("/user/denied")
	public String denied(Model model, Authentication auth, HttpServletRequest req) {
		AccessDeniedException ade = (AccessDeniedException) req.getAttribute(WebAttributes.ACCESS_DENIED_403);
		model.addAttribute("errMsg", ade);
		return "user/denied";
	}

	@RequestMapping("/admin/")
	public String listUser(Model model) {
		return "admin/main";
	}

	@RequestMapping("/user/insertUser.do")
	public String insertUser(@RequestParam String userid, @RequestParam String passwd, @RequestParam String name,
			@RequestParam String authority) {
		Map<String, String> map = new HashMap<>();
		map.put("userid", userid);
		System.out.println("함호화 전의 비번:" + passwd);
		String encryptPassword = passwordEncoder.encode(passwd);
		System.out.println("암호화 후의 비번:" + encryptPassword);
		map.put("passwd", encryptPassword);
		map.put("name", name);
		map.put("authority", authority);
		userDao.insertUser(map);
		return "user/login";
	}

	@RequestMapping("/user/logout.do")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}