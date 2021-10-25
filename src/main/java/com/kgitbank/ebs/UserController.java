package com.kgitbank.ebs;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
}
