package com.kgitbank.ebs;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kgitbank.ebs.model.TestDTO;
import com.kgitbank.ebs.service.mainMapper;

@Controller
public class TestController {
	@Autowired
	private mainMapper mainMapper;
	
	@RequestMapping(value = "test.do")
	public String test(HttpServletRequest req) {
		List<TestDTO> list = mainMapper.listTest();
		
		return "test";
	}
}
