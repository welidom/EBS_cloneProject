package com.kgitbank.ebs;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kgitbank.ebs.model.FaqDTO;
import com.kgitbank.ebs.model.NoticeDTO;
import com.kgitbank.ebs.service.faqMapper;
import com.kgitbank.ebs.service.noticeMapper;
import com.kgitbank.ebs.utils.Includes;

@Controller
public class MainController {
	@Inject
	private faqMapper faqmapper;
	@Inject
	private noticeMapper noticemapper;

	@RequestMapping(value="/main.do")
	public String mainNotice(HttpServletRequest req) {
		HttpSession session = req.getSession();
		
		String user = req.getParameter("login");
		if (user != null) {
			if(user.equals("0")) {
				session.setAttribute("UserId", null);
			}else {
				session.setAttribute("UserId", req.getParameter("login"));
			}
		}else {
			session.setAttribute("UserId", null);
		}
		
		List<NoticeDTO> list = noticemapper.listNotice("mainPage");
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
		NoticeDTO dto = noticemapper.getNotice(1, "ex");
		dto.setContent(cut(dto.getContent(), 7));
		NoticeDTO service = noticemapper.getNotice(2, "ex");
		service.setContent(cut(service.getContent(), 20));
		List<FaqDTO> faqlist = faqmapper.faqList();
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
	
	
}
