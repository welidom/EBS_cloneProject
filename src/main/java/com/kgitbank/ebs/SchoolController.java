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
		
		
		String url="schoolMainPage.do";
		
		ModelAndView mav = new ModelAndView("pass");
		mav.addObject("url", url);
		return mav;
	}
	@RequestMapping(value = "/schoolMainPage.do")
	public String pageMain(HttpServletRequest req) {
		
		
		return "school/main";
	}
}
