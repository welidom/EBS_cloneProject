package com.kgitbank.ebs;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.ModelAndView;

import com.kgitbank.ebs.model.FaqDTO;
import com.kgitbank.ebs.model.ManualDTO;
import com.kgitbank.ebs.model.NoticeDTO;
import com.kgitbank.ebs.service.mainMapper;

@Controller
public class mainController {
	@Autowired
	private mainMapper mainMapper;
	
	@RequestMapping(value="/main.do", method = RequestMethod.GET)
	public String mainNotice(HttpServletRequest req) {
		List<NoticeDTO> list = mainMapper.listNotice();
		if(list.size() > 4) {
			list = list.subList(0, 4);
		}
		for(int i = 0; i < list.size(); i++) {
			list.get(i).setReg_date(list.get(i).getReg_date().substring(0, 10));
			list.get(i).setReg_date(list.get(i).getReg_date().replaceAll("-", "."));
			String arr[] = list.get(i).getSubject().split("");
			if(arr.length > 40) {
				String str = "";
				for(String c : Arrays.copyOfRange(arr, 0, 40)) {
					str += c;
				}
				list.get(i).setSubject(str+"...");
			}
		}
		NoticeDTO dto = mainMapper.getNotice(1);
		if(dto != null) {
			String[] arr = dto.getContent().split("\n");	
			if(arr.length > 7) {
				String str = "";
				for(String c: Arrays.copyOfRange(arr, 0, 7)) {
					str += c;
					str += "<br>";
				}
				str+="...";
				dto.setContent(str);
			}else {dto.setContent(dto.getContent().replace("\r\n", "<br>"));}
		}
		List<FaqDTO> faqlist = mainMapper.faqList();
		if(faqlist.size() > 8) {
			faqlist = faqlist.subList(0, 8);
		}
		req.setAttribute("listFaq", faqlist);
		req.setAttribute("footerContent", getFooter());
		req.setAttribute("listNotice", list);
		req.setAttribute("getNotice", dto);
		return "main";
		
	}
	public String[][] getFooter() {
		String[][] footerContent =  {{"logo01", ""},{"logo02", ""},{"logo03", ""},{"logo04", ""},{"logo05", ""},{"logo06", ""},{"logo07", ""},{"logo08", ""},{"logo09", ""}};
		return footerContent;
	}
	@RequestMapping(value="/manual.do", method = RequestMethod.GET)
	public String manual(HttpServletRequest req) {
		List<ManualDTO> videoManual = mainMapper.getManual(0);
		List<ManualDTO> documentManual = mainMapper.getManual(1);
		
		req.setAttribute("videoManual", setDate(videoManual));
		req.setAttribute("documentManual", setDate(documentManual));
		req.setAttribute("footerContent", getFooter());
		return "manual/main";
	}
	public List<ManualDTO> setDate(List<ManualDTO> list){
		for(int i = 0; i < list.size(); i++) {
			list.get(i).setReg_date(list.get(i).getReg_date().substring(0, 10));
			list.get(i).setReg_date(list.get(i).getReg_date().replaceAll("-", "."));
		}
		return list;
	}
	@RequestMapping(value="/insertManual.do", method=RequestMethod.GET)
	public String insertManualForm(HttpServletRequest req) {
		req.setAttribute("footerContent", getFooter());
		return "manual/form";
	}
	
	@RequestMapping(value="/insertManualPro.do", method = RequestMethod.POST)
	public ModelAndView insertManualPro(HttpServletRequest req, ManualDTO dto){
		int res = mainMapper.insertManual(dto);
		Object file = req.getAttribute("content");
		System.out.println(file);
		String msg,url;
		if(res > 0) {
			msg="메뉴얼 등록 성공";
			url = "manual.do";
		}else {
			msg="메뉴얼 등록 실페";
			url = "insertManual.do";
		}
		req.setAttribute("footerContent", getFooter());
		ModelAndView mav = new ModelAndView("message");
		mav.addObject("msg", msg);
		mav.addObject("url", url);
		return mav;
	}
}
