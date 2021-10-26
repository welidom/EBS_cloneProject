package com.kgitbank.ebs;


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
		System.out.println(dto.toString());
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
	@ResponseBody
	@RequestMapping(value = "checkOverlab.do")
	public boolean checkOverlab(String userId) {
		boolean check = usermapper.checkOverlab(userId);
		return check;
	}
}
