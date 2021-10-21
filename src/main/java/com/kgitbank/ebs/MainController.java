package com.kgitbank.ebs;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kgitbank.ebs.model.FaqDTO;
import com.kgitbank.ebs.model.NoticeDTO;
import com.kgitbank.ebs.service.mainMapper;

@Controller
public class MainController {
	@Autowired
	private mainMapper mainMapper;
	
	@RequestMapping(value="/main.do", method = RequestMethod.GET)
	public String mainNotice(HttpServletRequest req) {
		List<NoticeDTO> list = mainMapper.listNotice("mainPage");
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
		NoticeDTO dto = mainMapper.getNotice(1, "ex");
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
		req.setAttribute("footerContent", Includes.getFooter());
		req.setAttribute("listNotice", list);
		req.setAttribute("getNotice", dto);
		return "main";
		
	}
	
	
	
}
