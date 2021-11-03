package com.kgitbank.ebs.interceptor;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.kgitbank.ebs.model.UserDTO;
import com.kgitbank.ebs.service.UserService;

public class ProfileInterceptor extends HandlerInterceptorAdapter{
	@Inject
	private UserService userService;
	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler)
			throws Exception {
		HttpSession session = req.getSession();
		UserDTO dto = userService.getUser((String) session.getAttribute("UserId"));
		if(dto == null) {
			resp.sendRedirect("login");
			return false;
		}else if(dto.getPermit() == 3) {
			resp.sendRedirect("manage");
			return false;
		}
		return true;
	}
}
