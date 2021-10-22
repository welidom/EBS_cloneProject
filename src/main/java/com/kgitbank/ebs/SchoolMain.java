package com.kgitbank.ebs;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SchoolMain {
	
	@RequestMapping(value = "/schoolMain.do")
	public ModelAndView main(HttpServletRequest req, HttpSession session) {
		
		String msg,url;
			
		String UserId = (String) session.getAttribute("UserId");
		
		System.out.println(UserId);
		
		if(UserId.equals("1")) {
			msg="학생 메인페이지로 이동합니다";
			url="schoolMainStudent.do";
		}
		else if(UserId.equals("2")){
			msg="선생님 메인페이지로 이동합니다";
			url="schoolMainTeacher.do";
		}else if(UserId.equals("3")){
			msg="관리자 메인페이지로 이동합니다";
			url="schoolMainAdmin.do";
		}else {
			msg="사용자 아이디가 부적합합니다";
			url="main.do";
		}
		
		ModelAndView mav = new ModelAndView("message");
		mav.addObject("msg", msg);
		mav.addObject("url", url);
		return mav;
	}
	@RequestMapping(value = "/schoolMainStudent.do")
	public String studentMain(HttpServletRequest req, HttpSession session) {
		
		
		return "school/student/main";
	}
}
