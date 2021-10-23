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
	public String list(Model model, @Param(value = "cno") int cno){
		
		
		if(cno == 0) {
			model.addAttribute("list", faqmapper.faqReadcountList());
		}else {
			model.addAttribute("list", faqmapper.faqList(cno));
		}
		model.addAttribute("listCategory", Includes.getFaqCategory());
		model.addAttribute("footerContent", Includes.getFooter());
		model.addAttribute("cno", cno);
		return "faq/listView";
	}
	
	@ResponseBody
	@RequestMapping(value = "/faqReadcount.do")
	public void readcounts(@RequestParam(value="bno") int num) throws Exception{
		faqmapper.faqreadcount(num);
	}
	
}