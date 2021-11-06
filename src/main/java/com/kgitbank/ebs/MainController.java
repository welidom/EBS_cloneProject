package com.kgitbank.ebs;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kgitbank.ebs.model.FaqDTO;
import com.kgitbank.ebs.model.NoticeDTO;
import com.kgitbank.ebs.service.FaqService;
import com.kgitbank.ebs.service.NoticeService;
import com.kgitbank.ebs.utils.Includes;

@Controller
public class MainController {
	@Inject
	private FaqService faqService;
	@Inject
	private NoticeService noticeService;

	@RequestMapping(value="/")
	public String mainNotice(HttpServletRequest req) {
		List<NoticeDTO> list = noticeService.listNotice("mainPage");
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
		NoticeDTO dto = noticeService.getNotice(1, "ex");
		dto.setContent(cut(dto.getContent(), 7));
		NoticeDTO service = noticeService.getNotice(2, "ex");
		service.setContent(cut(service.getContent(), 20));
		List<FaqDTO> faqlist = faqService.faqList();
		if(faqlist.size() > 8) {
			faqlist = faqlist.subList(0, 8);
		}
		req.setAttribute("listFaq", faqlist);
		req.setAttribute("footerContent", Includes.getFooter());
		req.setAttribute("listNotice", list);
		req.setAttribute("getNotice", dto);
		req.setAttribute("getServiceNotice", service);
		req.setAttribute("faqCategoryList", Includes.getFaqCategory());
		return "main";
		
	}
	public String cut(String content, int len) {
		String[] arr = content.split("<br>");
		if(arr.length > len) {
			String str = "";
			for(String c: Arrays.copyOfRange(arr, 0, len)) {
				str += c;
				str += "<br>";
			}
			str+="...";
			return str;
		}else {
			return content;
		}
	}
	@RequestMapping(value = "/inMaking")
	public String inMaking(HttpServletRequest req) {
		
		req.setAttribute("footerContent", Includes.getFooter());
		return "inMaking";
	}
	
}
