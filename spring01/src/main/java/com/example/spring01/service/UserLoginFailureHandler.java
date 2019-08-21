package com.example.spring01.service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class UserLoginFailureHandler implements AuthenticationFailureHandler {
	@Override
	public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse res, AuthenticationException auth)
			throws IOException, ServletException {
		req.setAttribute("errMsg", "아이디 또는 비밀번호가 일치하지 않습니다.");
		req.getRequestDispatcher("/WEB-INF/views/user/login.jsp").forward(req, res);
	}
}
