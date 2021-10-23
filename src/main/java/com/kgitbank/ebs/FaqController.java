package com.kgitbank.ebs;




import javax.inject.Inject;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kgitbank.ebs.service.faqMapper;
import com.kgitbank.ebs.utils.Includes;

@Controller
public class FaqController {
	@Inject
	private faqMapper faqmapper;
	
	@RequestMapping(value="/faqList.do", method=RequestMethod.GET)
	public String list(Model model){
		
		String[] name = {"자주 찾는 질문 ", "회원","클래스 이용", "학습", "클래스 개설/관리", "학습 관리", "화상 수업", "강좌 관리", "기타"};
		
		model.addAttribute("list", faqmapper.faqList());
		model.addAttribute("listCategory", name);
		model.addAttribute("footerContent", Includes.getFooter());
		return "faq/listView";
	}
	
	@ResponseBody
	@RequestMapping(value = "/faqReadcount.do")
	public void readcounts(@RequestParam(value="bno") int num) throws Exception{
		faqmapper.faqreadcount(num);
	}
	
}