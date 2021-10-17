package com.kgitbank.ebs;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.kgitbank.ebs.model.ManualDTO;
import com.kgitbank.ebs.model.NoticeDTO;
import com.kgitbank.ebs.service.mainMapper;

@Controller
public class NoticeController {

	@Autowired
	private mainMapper mainMapper;
	
	@RequestMapping(value = "/notice.do", method=RequestMethod.GET)
	public String noticeMain(HttpServletRequest req) {
		List<NoticeDTO> notice = mainMapper.listNotice("main");
		
		int count = 0;
		for(NoticeDTO dto: notice) {
			if(dto.getMustRead() == 0) {
				count ++;
			}
		}
		
		req.setAttribute("notice", setDate(notice));
		req.setAttribute("count", count);
		req.setAttribute("footerContent", Includes.getFooter());
		
		return "notice/main";
	}
	public List<NoticeDTO> setDate(List<NoticeDTO> list){
		for(int i = 0; i < list.size(); i++) {
			list.get(i).setReg_date(list.get(i).getReg_date().substring(0, 10));
			list.get(i).setReg_date(list.get(i).getReg_date().replaceAll("-", "."));
		}
		return list;
	}
	@RequestMapping(value="/noticeContent.do", method = RequestMethod.GET)
	public String showContent(HttpServletRequest req, @Param("num") int num, @Param("no") int no) {
		NoticeDTO dto = mainMapper.getNotice(num, "read");
		
		dto.setReg_date(dto.getReg_date().substring(0, 10));
		dto.setReg_date(dto.getReg_date().replaceAll("-", "."));
		
		req.setAttribute("footerContent", Includes.getFooter());
		req.setAttribute("dto", dto);
		req.setAttribute("no", no);
		return "notice/content";
	}
	@RequestMapping(value = "/insertNotice.do", method= RequestMethod.GET)
	public String insertNoticeForm(HttpServletRequest req) {

		req.setAttribute("footerContent", Includes.getFooter());
		return "notice/insertForm";
	}
	@RequestMapping(value="insertNotice.do", method=RequestMethod.POST)
public ModelAndView upload(MultipartHttpServletRequest req){
		
		NoticeDTO dto = new NoticeDTO();
		
		MultipartFile uploadFile = req.getFile("file");
		
		String savePath = "D:/Files/noticeFiles";
		
		String originalFileName= "";
			
		try {
			if(!uploadFile.isEmpty()) {
				Includes.saveFile(uploadFile, savePath);
				originalFileName = new String(uploadFile.getOriginalFilename().getBytes("8859_1"), "utf-8");
			}
			dto.setAttach(originalFileName);
			dto.setContent(new String(req.getParameter("content").getBytes("8859_1"), "utf-8"));
			dto.setCategory(new String(req.getParameter("category").getBytes("8859_1"), "utf-8"));
			dto.setSubject(new String(req.getParameter("subject").getBytes("8859_1"), "utf-8"));				
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] mr = req.getParameterValues("mustRead");
		if(mr != null) {
			dto.setMustRead(1);
		}else {
			dto.setMustRead(0);
		}
		
		dto.setContent(dto.getContent().replaceAll("\n", "<br>"));
		
		
		int res = mainMapper.insertNotice(dto);
		String msg,url;
		if(res > 0) {
			msg="공지 등록 성공";
			url = "notice.do";
		}else {
			msg="공지 등록 실페";
			url = "main.do";
		}
		req.setAttribute("footerContent", Includes.getFooter());
		ModelAndView mav = new ModelAndView("message");
		mav.addObject("msg", msg);
		mav.addObject("url", url);
		return mav;
	}
}
