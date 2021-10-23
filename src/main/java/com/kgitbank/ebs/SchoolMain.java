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
		
		String url="schoolMainPage.do";
		String UserId = (String) session.getAttribute("UserId");
		
		if(UserId != null) {
			if(!UserId.equals("1") && !UserId.equals("2") && !UserId.equals("3")) {
				url="main.do";
			}
		}else {
			url="main.do";
		}
		ModelAndView mav = new ModelAndView("pass");
		mav.addObject("url", url);
		return mav;
	}
	@RequestMapping(value = "/schoolMainPage.do")
	public String studentMain(HttpServletRequest req, HttpSession session) {
		
		
		return "school/main";
	}
}
