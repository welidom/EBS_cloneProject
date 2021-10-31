package com.kgitbank.ebs;


import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kgitbank.ebs.model.SchoolDTO;
import com.kgitbank.ebs.model.UserDTO;
import com.kgitbank.ebs.service.MailService;
import com.kgitbank.ebs.service.SchoolService;
import com.kgitbank.ebs.service.UserService;
import com.kgitbank.ebs.utils.Includes;

@Controller
public class UserController {
	@Inject
	private UserService userService;
	@Inject
	private SchoolService schoolService;
	@Inject
	private MailService mailService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginForm(HttpServletRequest req) {
		
		req.setAttribute("footerContent", Includes.getFooter());
		return "user/login";
	}
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView loginPro(HttpServletRequest req, UserDTO dto) {
		UserDTO check = userService.getUser(dto.getId());
		
		String url,msg,path;
		HttpSession session = req.getSession();
		
		if(check == null || !check.getPw().equals(dto.getPw())) {
			path = "message";
			url = "login";
			msg = "아이디 또는 비밀번호가 일치하지 않습니다 .";
		}else{
			session.setAttribute("UserId", check.getId());
			session.setAttribute("UserPermit", check.getPermit());
			path = "pass";
			url = "main";
			msg = "";
		}
		
		ModelAndView mav = new ModelAndView(path);
		mav.addObject("url", url);
		mav.addObject("msg", msg);
		return mav;
	}
	
	@RequestMapping(value = "/signUp", method=RequestMethod.GET)
	public String signUp() {
		
		return "user/signup1";
	}
	@RequestMapping(value = "/term")
	public String term(){
		
		return "user/signup2";
	}
	@RequestMapping(value = "/signUp", method=RequestMethod.POST)
	public String mainSignUp(HttpServletRequest req,UserDTO dto) {
		userService.newUser(dto);
		HttpSession session = req.getSession();
		session.setAttribute("UserId", dto.getId());
		return "user/signup3";
	}
	@RequestMapping(value = "/logout")
	public ModelAndView logout(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.removeAttribute("UserId");
		
		ModelAndView mav = new ModelAndView("pass");
		mav.addObject("url", "main");
		return mav;
	}
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public ModelAndView profile() {
		ModelAndView mav = new ModelAndView("pass");
		mav.addObject("url", "main");
		return mav;
	}
	@RequestMapping(value = "/profileUpdate", method = RequestMethod.POST)
	public ModelAndView updateProfile(UserDTO dto, HttpServletRequest req) {
		HttpSession session = req.getSession();
		dto.setId((String) session.getAttribute("UserId"));
		int res = userService.updateUser(dto);
		String msg,url;
		if(res > 0) {
			msg = "프로필 수정 성공";
			url = "mainSchool";
		}else {
			msg = "프로필 수정 실페";
			url = "profile";
		}
		ModelAndView mav = new ModelAndView("message");
		mav.addObject("msg", msg);
		mav.addObject("url", url);
		return mav;
	}
	@RequestMapping(value = "/studentProfile")
	public String studentProfile(HttpServletRequest req) {
		HttpSession session = req.getSession();
		UserDTO dto = userService.getUser((String) session.getAttribute("UserId"));
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
			grade = "고등학생" + (age-16)+"학년";
		}else {
			grade = "---";
		}
		req.setAttribute("school", schoolService.getSchool(dto.getSchoolId()));
		req.setAttribute("dto", dto);
		req.setAttribute("grade", grade);
		req.setAttribute("footerContent", Includes.getFooter());
		return "user/profile";
	}
	@RequestMapping(value="/manage", method = RequestMethod.GET)
	public String manage(HttpServletRequest req) {
		List<UserDTO> userList = userService.userList();
		List<SchoolDTO> schoolList = schoolService.schoolList();
		
		req.setAttribute("userList", userList);
		req.setAttribute("schoolList", schoolList);
		req.setAttribute("footerContent", Includes.getFooter());
		return "user/admin/manage";
	}
	@RequestMapping(value = "/deleteUser")
	public ModelAndView deleteUser(String id) {
		
		ModelAndView mav = new ModelAndView();
		return mav;
	}
	@RequestMapping("/auth")
	public String signUp(String email, HttpServletRequest req) {
		HttpSession session = req.getSession();
		String authKey = mailService.sendAuthMail(email);
		userService.setAuth(authKey,(String) session.getAttribute("UserId"));
		return "user/teacher/auth";
	}

	@RequestMapping("/check")
	public ModelAndView check(String email, String authKey, HttpServletRequest req) {
		UserDTO dto = userService.checkUser(email, authKey);
		HttpSession session = req.getSession();
		String msg,url;
		if(dto.getId() != null) {
			msg="인증되었습니다.";
			url = "main";
			session.setAttribute("UserId", dto.getId());
			userService.setPermit(dto.getId(), 2);
		}else {
			msg="인증에 실페하셨습니다 .";
			url = "main";
		}
		ModelAndView mav = new ModelAndView("message");
		mav.addObject("msg", msg);
		mav.addObject("url", url);
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/changeSchool")
	public void changeSchool(String schoolId, HttpServletRequest req) {
		HttpSession session = req.getSession();
		userService.setSchool(schoolId, (String) session.getAttribute("UserId"));
	}

	@ResponseBody
	@RequestMapping(value = "/checkOverlab")
	public boolean checkOverlab(String userId) {
		boolean check = userService.checkOverlab(userId);
		return check;
	}
}
