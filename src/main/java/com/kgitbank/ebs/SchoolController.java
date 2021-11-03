package com.kgitbank.ebs;


import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.kgitbank.ebs.model.SchoolDTO;
import com.kgitbank.ebs.model.UserDTO;
import com.kgitbank.ebs.service.SchoolService;
import com.kgitbank.ebs.service.UserService;

@Controller
public class SchoolController {
	@Inject
	private UserService userService;
	
	@Inject
	private SchoolService schoolService;
	
	@RequestMapping(value = "/mainSchool")
	public ModelAndView main(HttpServletRequest req) {
		
		HttpSession session = req.getSession();

		UserDTO dto = userService.getUser((String) session.getAttribute("UserId"));
		int userPermit = dto.getPermit();
		String url = "main";
		if(userPermit == 1) {
			url = "studentMain";
		}else if(userPermit == 2) {
			url = "teacherMain";
		}
		if(dto.getSchoolId() == null) {
			url = "profile";
		}
		ModelAndView mav = new ModelAndView("pass");
		mav.addObject("url", url);
		return mav;
	}
	@RequestMapping(value = "studentMain")
	public String studentMain() {
		return "";
	}
	@RequestMapping(value="/getSchool", method = RequestMethod.GET)
	public String getSchoolForm(HttpServletRequest req) {
		
		return "school/getSchoolForm";
	}
	@RequestMapping(value = "/getSchool", method = RequestMethod.POST)
	public ModelAndView getSchoolPro(UserDTO dto) {
		
		userService.updateUser(dto);
		
		ModelAndView mav = new ModelAndView("pass");
		mav.addObject("url", "mainSchool");
		return mav;
	}
	@RequestMapping(value = "/insertSchool", method = RequestMethod.GET)
	public String insertSchoolForm() {
		
		return "user/admin/addSchool";
	}
	@RequestMapping(value = "/updateSchool", method = RequestMethod.GET)
	public String updateSchoolForm(String schoolId,HttpServletRequest req) {
		SchoolDTO dto = schoolService.getSchool(schoolId);
		req.setAttribute("dto", dto);
		return "user/admin/updateSchool";
	}
	@ResponseBody
	@RequestMapping(value = "/updateSchool", method = RequestMethod.POST)
	public void updateSchoolPro(SchoolDTO dto) {
		schoolService.updateSchool(dto);
	}
	@ResponseBody
	@RequestMapping(value = "/insertSchool", method = RequestMethod.POST)
	public void insertSchoolPro(String schoolName, String schoolManager) {
		schoolService.insertSchool(schoolName, schoolManager);
	}
	
	@ResponseBody
	@RequestMapping(value = "/checkSchoolOverlab")
	public boolean checkSchoolOverlab(String schoolName) {
		return schoolService.checkSchool(schoolName);
	}
	@ResponseBody
	@RequestMapping(value="/deleteSchool")
	public void deleteSchool(String id) {
		schoolService.deleteSchool(id);
	}
	@ResponseBody
	@RequestMapping(value="/searchSchool", produces = "application/text; charset=utf8")
	public String listSchool(String searchFor) {
		List<SchoolDTO> list = schoolService.searchSchool(searchFor);
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
