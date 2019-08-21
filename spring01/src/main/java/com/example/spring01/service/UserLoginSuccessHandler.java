package com.example.spring01.service;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import com.example.spring01.model.dto.UserDTO;

public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {
//인증이 성공한 경우의 코드
	@Override
	public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res, Authentication auth)
			throws IOException, ServletException {
		UserDTO dto = (UserDTO) auth.getPrincipal();
		System.out.println(dto);
		String msg = auth.getName() + "님 환영합니다.";
		req.setAttribute("msg", msg);
// forward, redirect 가능
		RequestDispatcher rd = req.getRequestDispatcher("/");
		rd.forward(req, res);
//res.sendRedirect(req.getContextPath());
	}
}