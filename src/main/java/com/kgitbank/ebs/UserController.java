package com.kgitbank.ebs;


import java.util.ArrayList;
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

import com.google.api.client.util.Value;
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
			url = "/";
			msg = "";
		}
		
		ModelAndView mav = new ModelAndView(path);
		mav.addObject("url", url);
		mav.addObject("msg", msg);
		return mav;
	}
	@RequestMapping(value = "findId", method = RequestMethod.GET)
	public String findIdForm() {
		return "user/findIdForm";
	}
	@RequestMapping(value = "findId", method = RequestMethod.POST)
	public ModelAndView findIdPro(String email) {
		List<UserDTO> list = userService.emailUserList(email);
		List<String> ids = new ArrayList<String>();
		for(UserDTO dto:list) {
			ids.add(dto.getId());
		}
		
		mailService.sendId(email, ids);
		
		ModelAndView mav = new ModelAndView("message");
		mav.addObject("msg","이메일이 발송되었습니다");
		mav.addObject("url", "login");
		return mav;
	}
	@RequestMapping(value = "/term", method=RequestMethod.GET)
	public String term() {
		
		return "user/term";
	}
	@RequestMapping(value = "/signUpForm", method = RequestMethod.POST)
	public String signUpForm(){
		return "user/signup";
	}
	@RequestMapping(value = "/signUpPro", method=RequestMethod.POST)
	public String mainSignUp(HttpServletRequest req,UserDTO dto) {
		userService.newUser(dto);
		HttpSession session = req.getSession();
		session.setAttribute("UserId", dto.getId());
		return "user/signupCompl";
	}
	@RequestMapping(value = "/logout")
	public ModelAndView logout(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.removeAttribute("UserId");
		
		ModelAndView mav = new ModelAndView("pass");
		mav.addObject("url", "/");
		return mav;
	}
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public ModelAndView profile(HttpServletRequest req) {
		UserDTO dto = userService.getUser((String) req.getSession().getAttribute("UserId"));
		String url;
		if(dto.getPermit() == 1) {
			url = "studentProfile.do";
		}else if(dto.getPermit() == 2) {
			url = "teacherProfile.do";
		}else {
			url = "/";
		}
		ModelAndView mav = new ModelAndView("pass");
		mav.addObject("url", url);
		
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
		return "user/student/profile";
	}
	@RequestMapping(value = "/teacherProfile")
	public String teacherProfile(HttpServletRequest req) {
		HttpSession session = req.getSession();
		UserDTO dto = userService.getUser((String) session.getAttribute("UserId")); 
		String birth = "";
		for(int i = 0; i<4; i++) {
			birth += dto.getBirth().split("")[i];
		}
		int year = Calendar.getInstance().get(Calendar.YEAR);
		int age = year - Integer.parseInt(birth);
		
		req.setAttribute("school", schoolService.getSchool(dto.getSchoolId()));
		req.setAttribute("dto", dto);
		req.setAttribute("age", age);
		req.setAttribute("footerContent", Includes.getFooter());
		return "user/teacher/profile";
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
		if(dto != null) {
			msg="인증되었습니다.";
			url = "/";
			session.setAttribute("UserId", dto.getId());
			userService.setPermit(dto.getId(), 2);
		}else {
			msg="인증에 실페하셨습니다 .";
			url = "/";
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
	@RequestMapping(value = "/checkUserOverlab")
	public boolean checkUserOverlab(String userId) {
		boolean check = userService.checkUserOverlab(userId);
		return check;
	}
	
	@ResponseBody
	@RequestMapping(value = "/deleteUser")
	public void deleteUser(String id) {
		userService.deleteUser(id);
	}
}
