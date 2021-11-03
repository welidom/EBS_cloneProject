package com.kgitbank.ebs.interceptor;

import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.kgitbank.ebs.model.UserDTO;
import com.kgitbank.ebs.service.UserService;

public class PermitInterceptor extends HandlerInterceptorAdapter{
	@Inject
	private UserService userService;
	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler)
			throws Exception {
		HttpSession session = req.getSession();
		UserDTO dto = userService.getUser((String) session.getAttribute("UserId"));
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		if(dto == null || dto.getPermit() != 3) {
			out.println("<script>alert('권한이 불충분합니다.'); location.href='main';</script>");
			out.flush();
			return false;
		}	
		return true;
	}
}
