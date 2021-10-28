package com.kgitbank.ebs;


import java.util.Calendar;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kgitbank.ebs.model.UserDTO;
import com.kgitbank.ebs.service.userMapper;
import com.kgitbank.ebs.utils.Includes;

@Controller
public class UserController {
	@Inject
	private userMapper usermapper;
	
	@RequestMapping(value = "login.do", method = RequestMethod.GET)
	public String loginForm(HttpServletRequest req) {
		
		req.setAttribute("footerContent", Includes.getFooter());
		return "user/login";
	}
	@RequestMapping(value = "login.do", method = RequestMethod.POST)
	public ModelAndView loginPro(HttpServletRequest req, UserDTO dto) {
		UserDTO check = usermapper.getUser(dto.getId());
		
		String url,msg,path;
		HttpSession session = req.getSession();
		
		if(check == null) {
			path = "message";
			url = "login.do";
			msg = "존재하지 않는 아이디입니다.";
		}else if(check.getPw().equals(dto.getPw())) {
			session.setAttribute("UserId", check.getId());
			session.setAttribute("UserPermit", check.getPermit());
			path = "pass";
			url = "main.do";
			msg = "";
		}else {
			path = "message";
			url = "login.do";
			msg = "잘못된 비밀번호입니다.";
		}
		
		ModelAndView mav = new ModelAndView(path);
		mav.addObject("url", url);
		mav.addObject("msg", msg);
		return mav;
	}
	
	@RequestMapping(value = "signUp.do", method=RequestMethod.GET)
	public String signUp() {
		
		return "user/signup1";
	}
	@RequestMapping(value = "term.do")
	public String term(){
		
		return "user/signup2";
	}
	@RequestMapping(value = "signUp.do", method=RequestMethod.POST)
	public String mainSignUp(HttpServletRequest req,UserDTO dto) {
		usermapper.newUser(dto);
		HttpSession session = req.getSession();
		session.setAttribute("UserId", dto.getId());
		session.setAttribute("UserPermit", 1);
		return "user/signup3";
	}
	@RequestMapping(value = "logout.do")
	public ModelAndView logout(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.removeAttribute("UserId");
		session.removeAttribute("UserPermit");
		
		ModelAndView mav = new ModelAndView("pass");
		mav.addObject("url", "main.do");
		return mav;
	}
	@RequestMapping(value = "profile.do")
	public ModelAndView profile() {
		ModelAndView mav = new ModelAndView("pass");
		mav.addObject("url", "main.do");
		return mav;
	}
	@RequestMapping(value = "studentProfile.do")
	public String studentProfile(HttpServletRequest req) {
		HttpSession session = req.getSession();
		UserDTO dto = usermapper.getUser((String) session.getAttribute("UserId"));
		String birth = "";
		for(int i = 0; i<4; i++) {
			birth += dto.getBirth().split("")[i];
		}
		int year = Calendar.getInstance().get(Calendar.YEAR);
		int age = year - Integer.parseInt(birth);
		String grade;
		if(age >= 8 && age <= 13) {
			grade = "초등학생 "+(age-7)+"학년";
		}else if(age > 13 && age <= 16) {
			grade = "중학생 " + (age-13)+"학년";
		}else if(age > 16 && age < 20) {
			grade = "고등학생 " + (age-16)+"학년";
		}else {
			grade = "---";
		}
		
		req.setAttribute("dto", dto);
		req.setAttribute("grade", grade);
		req.setAttribute("footerContent", Includes.getFooter());
		return "user/profile";
	}
	
	@ResponseBody
	@RequestMapping(value = "checkOverlab.do")
	public boolean checkOverlab(String userId) {
		boolean check = usermapper.checkOverlab(userId);
		return check;
	}
}
