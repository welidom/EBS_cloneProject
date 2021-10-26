package com.kgitbank.ebs;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SchoolController {
	
	@RequestMapping(value = "/mainSchool.do")
	public ModelAndView main(HttpServletRequest req) {
		
		HttpSession session = req.getSession();
		
		int userPermit = (int) session.getAttribute("UserPermit");
		System.out.println(userPermit);
		
		String url = "main.do";
		if(userPermit == 1) {
			url = "studentMain.do";
		}else if(session.getAttribute("UserPermit").equals("2")) {
			
		}
		
		ModelAndView mav = new ModelAndView("pass");
		mav.addObject("url", url);
		return mav;
	}
	@RequestMapping(value = "/schoolMainPage.do")
	public String pageMain(HttpServletRequest req) {
		
		return "school/main";
	}
	@RequestMapping(value = "studentMain.do")
	public String studentMain() {
		return "";
	}
}
