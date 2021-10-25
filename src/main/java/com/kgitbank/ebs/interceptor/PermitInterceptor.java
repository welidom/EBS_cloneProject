package com.kgitbank.ebs.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class PermitInterceptor extends HandlerInterceptorAdapter{
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler)
			throws Exception {
		HttpSession session = req.getSession();
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		if(session.getAttribute("aaUserId") == null) {
			out.println("<script>alert('권한이 불충분합니다.'); location.href='main.do';</script>");
			out.flush();
			return false;
		} else if(session.getAttribute("UserId") != null) {
			if(!session.getAttribute("UserId").equals("3")) {
				out.println("<script>alert('권한이 불충분합니다.'); location.href='main.do';</script>");
				out.flush();
				return false;
			}
		}
		return true;
	}
}
