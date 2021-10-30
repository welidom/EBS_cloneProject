package com.kgitbank.ebs.interceptor;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.kgitbank.ebs.model.UserDTO;
import com.kgitbank.ebs.service.userMapper;

public class ProfileInterceptor extends HandlerInterceptorAdapter{
	@Inject
	private userMapper usermapper;
	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler)
			throws Exception {
		HttpSession session = req.getSession();
		UserDTO dto = usermapper.getUser((String) session.getAttribute("UserId"));
		if(dto == null) {
			resp.sendRedirect("login.do");
			return false;
		}else if(dto.getPermit() == 1) {
			resp.sendRedirect("studentProfile.do");
			return false;
		}else if(dto.getPermit() == 2) {
			resp.sendRedirect("teacherProfile.do");
			return false;
		}else if(dto.getPermit() == 3) {
			resp.sendRedirect("manage.do");
			return false;
		}
		return true;
	}
}
