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
import com.kgitbank.ebs.service.schoolMapper;
import com.kgitbank.ebs.service.userMapper;
import com.kgitbank.ebs.utils.Includes;

@Controller
public class UserController {
	@Inject
	private userMapper usermapper;
	@Inject
	private schoolMapper schoolmapper;
	
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String loginForm(HttpServletRequest req) {
		
		req.setAttribute("footerContent", Includes.getFooter());
		return "user/login";
	}
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public ModelAndView loginPro(HttpServletRequest req, UserDTO dto) {
		UserDTO check = usermapper.getUser(dto.getId());
		
		String url,msg,path;
		HttpSession session = req.getSession();
		
		if(check == null || !check.getPw().equals(dto.getPw())) {
			path = "message";
			url = "login.do";
			msg = "아이디 또는 비밀번호가 일치하지 않습니다 .";
		}else{
			session.setAttribute("UserId", check.getId());
			session.setAttribute("UserPermit", check.getPermit());
			path = "pass";
			url = "main.do";
			msg = "";
		}
		
		ModelAndView mav = new ModelAndView(path);
		mav.addObject("url", url);
		mav.addObject("msg", msg);
		return mav;
	}
	
	@RequestMapping(value = "/signUp.do", method=RequestMethod.GET)
	public String signUp() {
		
		return "user/signup1";
	}
	@RequestMapping(value = "/term.do")
	public String term(){
		
		return "user/signup2";
	}
	@RequestMapping(value = "/signUp.do", method=RequestMethod.POST)
	public String mainSignUp(HttpServletRequest req,UserDTO dto) {
		usermapper.newUser(dto);
		HttpSession session = req.getSession();
		session.setAttribute("UserId", dto.getId());
		return "user/signup3";
	}
	@RequestMapping(value = "/logout.do")
	public ModelAndView logout(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.removeAttribute("UserId");
		
		ModelAndView mav = new ModelAndView("pass");
		mav.addObject("url", "main.do");
		return mav;
	}
	@RequestMapping(value = "/profile.do", method = RequestMethod.GET)
	public ModelAndView profile() {
		ModelAndView mav = new ModelAndView("pass");
		mav.addObject("url", "main.do");
		return mav;
	}
	@RequestMapping(value = "/profileUpdate.do", method = RequestMethod.POST)
	public ModelAndView updateProfile(UserDTO dto, HttpServletRequest req) {
		HttpSession session = req.getSession();
		dto.setId((String) session.getAttribute("UserId"));
		int res = usermapper.updateUser(dto);
		String msg,url;
		if(res > 0) {
			msg = "프로필 수정 성공";
			url = "mainSchool.do";
		}else {
			msg = "프로필 수정 실페";
			url = "profile.do";
		}
		ModelAndView mav = new ModelAndView("message");
		mav.addObject("msg", msg);
		mav.addObject("url", url);
		return mav;
	}
	@RequestMapping(value = "/studentProfile.do")
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
			grade = "고등학생" + (age-16)+"학년";
		}else {
			grade = "---";
		}
		req.setAttribute("school", schoolmapper.getSchool(dto.getSchoolId()));
		req.setAttribute("dto", dto);
		req.setAttribute("grade", grade);
		req.setAttribute("footerContent", Includes.getFooter());
		return "user/profile";
	}
	@RequestMapping(value="/manage.do", method = RequestMethod.GET)
	public String manage(HttpServletRequest req) {
		List<UserDTO> userList = usermapper.userList();
		List<SchoolDTO> schoolList = schoolmapper.schoolList();
		
		req.setAttribute("userList", userList);
		req.setAttribute("schoolList", schoolList);
		req.setAttribute("footerContent", Includes.getFooter());
		return "user/admin/manage";
	}
	@RequestMapping(value = "/certification.do", method = RequestMethod.GET)
	public String certificationForm() {
		
		return "user/teacher/certification";
	}
	@RequestMapping(value = "/certification.do", method = RequestMethod.POST)
	public ModelAndView certificationPro(HttpServletRequest req, SchoolDTO dto) {
		HttpSession session = req.getSession();
		SchoolDTO check = schoolmapper.getSchool(dto.getId());
		String msg,url;
		if(dto.getName().equals(check.getName())){
			usermapper.setSchool(dto.getId(), (String) session.getAttribute("UserId"));
			usermapper.setPermit((String) session.getAttribute("UserId"), 2);
			msg= "교사 인증 성공";
			url = "mainSchool.do";
		}else {
			msg="교사 인증 실페";
			url="profile.do";
		}
		ModelAndView mav = new ModelAndView("message");
		mav.addObject("msg", msg);
		mav.addObject("url", url);
		return mav;
	}
	@ResponseBody
	@RequestMapping(value = "/changeSchool.do")
	public void changeSchool(String schoolId, HttpServletRequest req) {
		HttpSession session = req.getSession();
		usermapper.setSchool(schoolId, (String) session.getAttribute("UserId"));
	}

	@ResponseBody
	@RequestMapping(value = "/checkOverlab.do")
	public boolean checkOverlab(String userId) {
		boolean check = usermapper.checkOverlab(userId);
		return check;
	}
}
