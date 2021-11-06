package com.kgitbank.ebs;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.kgitbank.ebs.model.UserDTO;
import com.kgitbank.ebs.service.FaqService;
import com.kgitbank.ebs.service.UserService;
import com.kgitbank.ebs.utils.Includes;

@Controller
public class FaqController {
	@Inject
	private FaqService faqService;
	@Inject
	private UserService userService;
	@RequestMapping(value="/faqList", method=RequestMethod.GET)
	public String list(HttpServletRequest req){
		req.setAttribute("list", faqService.faqReadcountList());
		req.setAttribute("listCategory", Includes.getFaqCategory());
		req.setAttribute("footerContent", Includes.getFooter());
		req.setAttribute("cno", 0);
		return "faq/listView";
	}
	@RequestMapping(value="/faqList", method=RequestMethod.POST)
	public String list(HttpServletRequest req, @Param(value = "cno") int cno){
		if(cno == 0) {
			req.setAttribute("list", faqService.faqReadcountList());
		}else {
			req.setAttribute("list", faqService.faqList(cno));
		}
		req.setAttribute("listCategory", Includes.getFaqCategory());
		req.setAttribute("footerContent", Includes.getFooter());
		req.setAttribute("cno", cno);
		return "faq/listView";
	}
	@RequestMapping(value = "/deleteFaq", method = RequestMethod.POST)
	public ModelAndView delete(@Param("bno") int bno, @Param("cno") int cno) {
		int res = faqService.deleteFaq(bno);
		String msg,url;
		if(res > 0) {
			msg = "faq 삭제성공";
			url= "faqList";
		}else {
			msg="faq 삭제 실페s";
			url = "/";
		}
		ModelAndView mav = new ModelAndView("message");
		mav.addObject("msg", msg);
		mav.addObject("url",url);
		return mav;
	}
	
	@RequestMapping(value = "/updateFaq", method = RequestMethod.POST)
	public String updateForm(HttpServletRequest req ,@Param("bno") int bno, @Param("cno") int cno) {
		
		req.setAttribute("cno", cno);
		req.setAttribute("update", faqService.getFaq(bno));
		req.setAttribute("listCategory", Includes.getFaqCategory());
		return "faq/updateView";
	}
	@RequestMapping(value="updateFaqQu", method = RequestMethod.POST)
	public ModelAndView updatePro(FaqDTO dto) {
		int res = faqService.updateFaq(dto);
		String msg, url;
		if(res > 0) {
			msg="faq 수정성공";
			url = "faqList";
		}else {
			msg="faq 수정실페";
			url = "/";
		}
		ModelAndView mav = new ModelAndView("message");
		mav.addObject("msg", msg);
		mav.addObject("url", url);
		return mav;
	}
	@RequestMapping(value = "/insertFaq", method = RequestMethod.GET)
	public String insertForm(HttpServletRequest req) {
		
		req.setAttribute("categoryList", Includes.getFaqCategory());
		req.setAttribute("footerContent", Includes.getFooter());
		return "faq/newQuPage";
	}
	@RequestMapping(value="/insertFaq", method = RequestMethod.POST)
	public ModelAndView insertPro(HttpServletRequest req,FaqDTO dto) {
		int res = faqService.insertFaq(dto);
		String msg, url;
		if (res > 0){
			msg = "faq 추가 성공";
			url = "faqList";
		}else {
			msg = "faq 추가실페";
			url = "/";
		}
		ModelAndView mav = new ModelAndView("message");
		mav.addObject("msg", msg);
		mav.addObject("url", url);
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/faqReadcount")
	public void readcounts(@RequestParam(value="bno") int num) throws Exception{
		faqService.faqreadcount(num);
	}
	
	
	@ResponseBody
	@RequestMapping(value="/searchFaq", produces = "application/text; charset=utf8")
	public String search(String keyword, int cno, HttpServletRequest req){
		HttpSession session = req.getSession();
		UserDTO dto = userService.getUser((String) session.getAttribute("UserId"));
		if(cno == 0) {
			List<FaqDTO> list = faqService.faqReadcountList(keyword);
			JsonObject jo = getJson(list);
			jo.addProperty("cno", cno);
			jo.addProperty("UserPermit", dto.getPermit());
			return jo.toString();
		}else {
			List<FaqDTO> list =  faqService.faqList(cno, keyword);
			JsonObject jo = getJson(list);
			jo.addProperty("cno", cno);
			jo.addProperty("UserPermit", dto.getPermit());
			return jo.toString();
		}
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/changeCategory", produces = "application/text; charset=utf8")
	public String categoryChanged(int cno, HttpServletRequest req) {
		HttpSession session = req.getSession();
		UserDTO dto = userService.getUser((String) session.getAttribute("UserId"));
		if(cno == 0) {
			List<FaqDTO> list = faqService.faqReadcountList();
			JsonObject jo = getJson(list);
			jo.addProperty("cno", cno);
			if(dto != null) {
				jo.addProperty("UserPermit", dto.getPermit());
			}else {
				jo.addProperty("UserPermit", 0);
			}
			return jo.toString();
		}else {
			List<FaqDTO> list = faqService.faqList(cno);
			JsonObject jo = getJson(list);
			jo.addProperty("cno", cno);
			if(dto != null) {
				jo.addProperty("UserPermit", dto.getPermit());
			}else {
				jo.addProperty("UserPermit", 0);
			}
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