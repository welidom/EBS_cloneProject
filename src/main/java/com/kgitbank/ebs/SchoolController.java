package com.kgitbank.ebs;


import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.kgitbank.ebs.model.UserDTO;
import com.kgitbank.ebs.service.schoolMapper;
import com.kgitbank.ebs.service.userMapper;

@Controller
public class SchoolController {
	@Inject
	private userMapper usermapper;
	
	@Inject
	private schoolMapper schoolmapper;
	
	@RequestMapping(value = "/mainSchool.do")
	public ModelAndView main(HttpServletRequest req) {
		
		HttpSession session = req.getSession();
		
		int userPermit = (int) session.getAttribute("UserPermit");
		
		String url = "main.do";
		if(userPermit == 1) {
			url = "studentMain.do";
		}else if(session.getAttribute("UserPermit").equals("2")) {
			
		}
		UserDTO dto = usermapper.getUser((String) session.getAttribute("UserId"));
		if(dto.getSchoolId() == null) {
			url = "profile.do";
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
	@RequestMapping(value="getSchool.do", method = RequestMethod.GET)
	public String getSchoolForm(HttpServletRequest req) {
		
		return "school/getSchoolForm";
	}
	@RequestMapping(value = "getSchool.do", method = RequestMethod.POST)
	public ModelAndView getSchoolPro(UserDTO dto) {
		
		usermapper.updateUser(dto);
		
		ModelAndView mav = new ModelAndView("pass");
		mav.addObject("url", "mainSchool.do");
		return mav;
	}
}
