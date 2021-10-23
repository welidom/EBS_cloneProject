package com.kgitbank.ebs;




import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kgitbank.ebs.model.FaqDTO;
import com.kgitbank.ebs.service.faqMapper;
import com.kgitbank.ebs.utils.Includes;

@Controller
public class FaqController {
	@Inject
	private faqMapper faqmapper;
	
	@RequestMapping(value="/faqList.do", method=RequestMethod.GET)
	public String list(HttpServletRequest req, @Param(value = "cno") int cno){
		if(cno == 0) {
			req.setAttribute("list", faqmapper.faqReadcountList());
		}else {
			req.setAttribute("list", faqmapper.faqList(cno));
		}
		req.setAttribute("listCategory", Includes.getFaqCategory());
		req.setAttribute("footerContent", Includes.getFooter());
		req.setAttribute("cno", cno);
		return "faq/listView";
	}
	@RequestMapping(value = "/faqList.do", method = RequestMethod.POST)
	public String search(HttpServletRequest req){
		String keyword = (String) req.getAttribute("keyword");
		int cno = Integer.parseInt((String) req.getAttribute("cno"));
		if(cno == 0) {
			List<FaqDTO> list = faqmapper.faqReadcountList(keyword);
			req.setAttribute("list", list);
		}else {
			req.setAttribute("list", faqmapper.faqList(cno));
		}
		req.setAttribute("listCategory", Includes.getFaqCategory());
		req.setAttribute("footerContent", Includes.getFooter());
		req.setAttribute("cno", cno);
		return "faq/listView";
	}
	@ResponseBody
	@RequestMapping(value = "/faqReadcount.do")
	public void readcounts(@RequestParam(value="bno") int num) throws Exception{
		faqmapper.faqreadcount(num);
	}
	
}