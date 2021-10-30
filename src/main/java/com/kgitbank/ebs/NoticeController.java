package com.kgitbank.ebs;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.kgitbank.ebs.model.NoticeDTO;
import com.kgitbank.ebs.service.noticeMapper;
import com.kgitbank.ebs.utils.Includes;

@Controller
public class NoticeController {

	@Inject
	private noticeMapper noticemapper;
	
	
	@RequestMapping(value = "/notice.do", method=RequestMethod.GET)
	public String noticeMain(HttpServletRequest req) {
		List<NoticeDTO> mustList = noticemapper.listNotice("must");
		List<NoticeDTO> notMustList = noticemapper.listNotice("notMust");
		
		
		req.setAttribute("mustList", setDate(mustList));
		req.setAttribute("notMustList", setDate(notMustList));
		req.setAttribute("footerContent", Includes.getFooter());
		
		return "notice/main";
	}
	@RequestMapping(value = "/notice.do", method=RequestMethod.POST)
	public String searchNotice(HttpServletRequest req) {
		String search = "";
		try {
			search = new String (req.getParameter("search").getBytes("8859_1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		
		String mode = req.getParameter("searchFor");
		
		List<NoticeDTO> mustList = noticemapper.listNotice("must");
		List<NoticeDTO> notMustList = noticemapper.searchNotice(search, mode);
		
		req.setAttribute("mustList", setDate(mustList));
		req.setAttribute("notMustList", setDate(notMustList));
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
	public String showContent(HttpServletRequest req, @Param("num") int num) {
		NoticeDTO dto = noticemapper.getNotice(num, "read");
		
		dto.setReg_date(dto.getReg_date().substring(0, 10));
		dto.setReg_date(dto.getReg_date().replaceAll("-", "."));
		
		req.setAttribute("footerContent", Includes.getFooter());
		req.setAttribute("dto", dto);
		return "notice/content";
	}
	@RequestMapping(value = "/insertNotice.do", method= RequestMethod.GET)
	public String insertForm(HttpServletRequest req) {

		req.setAttribute("footerContent", Includes.getFooter());
		return "notice/insertForm";
	}
	@RequestMapping(value="insertNotice.do", method=RequestMethod.POST)
	public ModelAndView uploadPro(MultipartHttpServletRequest req){
		
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
		
		
		int res = noticemapper.insertNotice(dto);
		String msg,url;
		if(res > 0) {
			msg="공지사항 추가성공";
			url = "notice.do";
		}else {
			msg="공지사항 추가 실페";
			url = "main.do";
		}
		req.setAttribute("footerContent", Includes.getFooter());
		ModelAndView mav = new ModelAndView("message");
		mav.addObject("msg", msg);
		mav.addObject("url", url);
		return mav;
	}
	@RequestMapping(value = "/deleteNotice.do", method = RequestMethod.GET)
	public String deleteForm(HttpServletRequest req) {
		List<NoticeDTO> mustList = noticemapper.listNotice("must");
		List<NoticeDTO> notMustList = noticemapper.listNotice("notMust");
		
		
		req.setAttribute("mustList", setDate(mustList));
		req.setAttribute("notMustList", setDate(notMustList));
		req.setAttribute("footerContent", Includes.getFooter());
		
		return "notice/deleteForm";
	}
	@RequestMapping(value = "/deleteNotice.do", method = RequestMethod.POST)
	public ModelAndView deletePro(HttpServletRequest req) {
		int res = -1;
		String msg,url;
		if(req.getParameterValues("nums") != null) {
			List<Integer> nums = new ArrayList<Integer>();
			for(String i: req.getParameterValues("nums")) {
				if(!i.equals("1") && !i.equals("2")) {
					nums.add(Integer.parseInt(i));
				}
			}
			if(!nums.isEmpty()) {
				res = noticemapper.deleteNotice(nums);
			}
		}
		if(res > 0) {
			msg="공지사항 삭제 성공";
			url="notice.do";
		} else if(res < 0) {
			msg = "삭제할 공지사항을 선택해주세요.";
			url="deleteNotice.do";
		}
		else {
			msg="공지사항 삭제 실페";
			url="main.do";
		}
		ModelAndView mav = new ModelAndView("message");
		mav.addObject("msg", msg);
		mav.addObject("url", url);
		return mav;
	}
	@RequestMapping(value="/updateNotice.do", method = RequestMethod.GET)
	public String updateForm(HttpServletRequest req, @Param("num") int num) {
		NoticeDTO dto = noticemapper.getNotice(num, "read");
		
		dto.setReg_date(dto.getReg_date().substring(0, 10));
		dto.setReg_date(dto.getReg_date().replaceAll("-", "."));
		
		dto.setContent(dto.getContent().replaceAll("<br>", ""));
		
		req.setAttribute("footerContent", Includes.getFooter());
		req.setAttribute("dto", dto);
		return "notice/updateForm";
	}
	@RequestMapping(value="updateNotice.do", method=RequestMethod.POST)
	public ModelAndView updatePro(MultipartHttpServletRequest req, @Param("num") int num){
		
		NoticeDTO dto = new NoticeDTO();
		
		dto.setNum(num);
		
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
		
		int res = noticemapper.updateNotice(dto);
		String msg,url;
		if(res > 0) {
			msg="공지사항 수정 성공";
			url = "notice.do";
		}else {
			msg="공지사항 수정 실페";
			url = "main.do";
		}
		req.setAttribute("footerContent", Includes.getFooter());
		ModelAndView mav = new ModelAndView("message");
		mav.addObject("msg", msg);
		mav.addObject("url", url);
		return mav;
	}
}
