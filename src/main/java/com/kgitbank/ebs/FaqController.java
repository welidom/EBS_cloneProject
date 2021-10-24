package com.kgitbank.ebs;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
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
	public String search(HttpServletRequest req, @Param("keyword") String keyword, @Param("cno") int cno){
		if(cno == 0) {
			req.setAttribute("list", faqmapper.faqReadcountList(keyword));
		}else {
			req.setAttribute("list", faqmapper.faqList(cno, keyword));
		}
		req.setAttribute("listCategory", Includes.getFaqCategory());
		req.setAttribute("footerContent", Includes.getFooter());
		req.setAttribute("cno", cno);
		return "faq/listView";
	}
	@RequestMapping(value = "/faqDelete.do", method = RequestMethod.POST)
	public ModelAndView delete(@Param("bno") int bno, @Param("cno") int cno) {
		int res = faqmapper.deleteFaq(bno);
		String msg,url;
		if(res > 0) {
			msg = "faq 삭제 성공";
			url= "faqList.do?cno="+cno;
		}else {
			msg="faq 삭제 실페";
			url = "main.do";
		}
		ModelAndView mav = new ModelAndView("message");
		mav.addObject("msg", msg);
		mav.addObject("url",url);
		return mav;
	}
	
	@RequestMapping(value = "/faqUpdate.do", method = RequestMethod.POST)
	public String updateForm(HttpServletRequest req ,@Param("bno") int bno, @Param("cno") int cno) {
		
		req.setAttribute("cno", cno);
		req.setAttribute("update", faqmapper.getFaq(bno));
		req.setAttribute("listCategory", Includes.getFaqCategory());
		return "faq/updateView";
	}
	@RequestMapping(value="faqUpdateQu.do", method = RequestMethod.POST)
	public ModelAndView updatePro(@Param("cno") int cno, FaqDTO dto) {
		int res = faqmapper.updateFaq(dto);
		System.out.println(dto.toString());
		String msg, url;
		if(res > 0) {
			msg="faq 수정 성공";
			url = "faqList.do?cno="+cno;
		}else {
			msg="faq 수정 실페";
			url = "main.do";
		}
		ModelAndView mav = new ModelAndView("message");
		mav.addObject("msg", msg);
		mav.addObject("url", url);
		return mav;
	}
	@RequestMapping(value = "/faqNewqu.do", method = RequestMethod.GET)
	public String insertForm(HttpServletRequest req) {
		
		req.setAttribute("categoryList", Includes.getFaqCategory());
		req.setAttribute("footerContent", Includes.getFooter());
		return "faq/newQuPage";
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/faqReadcount.do")
	public void readcounts(@RequestParam(value="bno") int num) throws Exception{
		faqmapper.faqreadcount(num);
	}
	
	@ResponseBody
	@RequestMapping(value = "/changeCategory.do", produces = "application/text; charset=utf8")
	public String categoryChanged(int cno) {
		if(cno == 0) {
			List<FaqDTO> list = faqmapper.faqReadcountList();
			JsonObject jo = getJson(list);
			jo.addProperty("cno", cno);
			return jo.toString();
		}else {
			List<FaqDTO> list = faqmapper.faqList(cno);
			JsonObject jo = getJson(list);
			jo.addProperty("cno", cno);
			return jo.toString();
		}
	}
	public JsonObject getJson(List<FaqDTO> list) {
		JsonObject jo = new JsonObject();
		JsonArray ja = new JsonArray();
		for(FaqDTO dto: list) {
			JsonObject jObj = new JsonObject();
			jObj.addProperty("num", dto.getNum());
			jObj.addProperty("cno", dto.getCategory());
			jObj.addProperty("question", dto.getQuestion());
			jObj.addProperty("answer", dto.getAnswer());
			jObj.addProperty("readcount", dto.getReadcount());
			ja.add(jObj);
		}
		jo.add("FaqObj", ja);
		JsonArray cate = new JsonArray();
		for(String str: Includes.getFaqCategory()) {
			JsonObject jObj = new JsonObject();
			jObj.addProperty("name", str);
			cate.add(jObj);
		}
		jo.add("category", cate);
		return jo;
	}
}