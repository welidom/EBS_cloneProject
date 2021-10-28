package com.kgitbank.ebs.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class ProfileInterceptor extends HandlerInterceptorAdapter{
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler)
			throws Exception {
		HttpSession session = req.getSession();
		if(session.getAttribute("UserPermit") == null) {
			resp.sendRedirect("login.do");
			return false;
		}else if((int) session.getAttribute("UserPermit") == 1) {
			resp.sendRedirect("studentProfile.do");
			return false;
		}else if((int) session.getAttribute("UserPermit") == 2) {
			resp.sendRedirect("teacherProfile.do");
			return false;
		}
		return true;
	}
}
