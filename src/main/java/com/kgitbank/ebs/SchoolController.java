package com.kgitbank.ebs;


import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.kgitbank.ebs.model.SchoolDTO;
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

		UserDTO dto = usermapper.getUser((String) session.getAttribute("UserId"));
		int userPermit = dto.getPermit();
		String url = "main.do";
		if(userPermit == 1) {
			url = "studentMain.do";
		}else if(userPermit == 2) {
			url = "teacherMain.do";
		}
		if(dto.getSchoolId() == null) {
			url = "profile.do";
		}
		ModelAndView mav = new ModelAndView("pass");
		mav.addObject("url", url);
		return mav;
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
	
	@ResponseBody
	@RequestMapping(value="/searchSchool.do", produces = "application/text; charset=utf8")
	public String listSchool(String searchFor) {
		List<SchoolDTO> list = schoolmapper.searchSchool(searchFor);
		JsonObject jo = new JsonObject();
		JsonArray ja = new JsonArray();
		for(SchoolDTO dto: list) {
			JsonObject Jobj = new JsonObject();
			Jobj.addProperty("id", dto.getId());
			Jobj.addProperty("name", dto.getName());
			ja.add(Jobj);
		}
		jo.add("schools", ja);
		return jo.toString();
	}
	
}
