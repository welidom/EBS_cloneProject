package com.kgitbank.ebs;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.kgitbank.ebs.model.ManualDTO;
import com.kgitbank.ebs.service.ManualService;
import com.kgitbank.ebs.utils.Includes;

@Controller
public class ManualController {
	
	@Inject
	private ManualService manualService;

	@RequestMapping(value="/manual", method = RequestMethod.GET)
	public String manual(HttpServletRequest req) {
		List<ManualDTO> videoManual = manualService.getManual(0);
		List<ManualDTO> documentManual = manualService.getManual(1);
		
		req.setAttribute("videoManual", setDate(videoManual));
		req.setAttribute("documentManual", setDate(documentManual));
		req.setAttribute("footerContent", Includes.getFooter());
		return "manual/main";
	}
	public List<ManualDTO> setDate(List<ManualDTO> list){
		for(int i = 0; i < list.size(); i++) {
			list.get(i).setReg_date(list.get(i).getReg_date().substring(0, 10));
			list.get(i).setReg_date(list.get(i).getReg_date().replaceAll("-", "."));
		}
		return list;
	}
	
	@RequestMapping(value="/insertManual", method=RequestMethod.GET)
	public String insertManualForm(HttpServletRequest req) {
		req.setAttribute("footerContent", Includes.getFooter());
		return "manual/insertForm";
	}
	
	@RequestMapping(value="/insertManualPro", method=RequestMethod.POST)
	public ModelAndView upload(@RequestParam("file") MultipartFile uploadFile, MultipartHttpServletRequest req){
		
		ManualDTO dto = new ManualDTO();
		
		dto.setType(1);
		
		String root_path = req.getSession().getServletContext().getRealPath("/");  
		String savePath = root_path+"resources/Files/manualFiles";
		
		String originalFileName= null;
		
		try {
			Includes.saveFile(uploadFile, savePath);
			originalFileName = new String(uploadFile.getOriginalFilename().getBytes("8859_1"), "utf-8");
			
			dto.setContent(originalFileName);
			dto.setCategory(new String(req.getParameter("category").getBytes("8859_1"), "utf-8"));
			dto.setSubject(new String(req.getParameter("subject").getBytes("8859_1"), "utf-8"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		int res = manualService.insertManual(dto);
		String msg,url;
		if(res > 0) {
			msg="메뉴얼 추가 성공";
			url = "manual";
		}else {
			msg="메뉴얼 추가 실페";
			url = "insertManual";
		}
		req.setAttribute("footerContent", Includes.getFooter());
		ModelAndView mav = new ModelAndView("message");
		mav.addObject("msg", msg);
		mav.addObject("url", url);
		return mav;
	}
	@RequestMapping(value="/insertManualPro", method = RequestMethod.GET)
	public ModelAndView insertManualPro(HttpServletRequest req ,ManualDTO dto){
		int res = manualService.insertManual(dto);
		String msg,url;
		if(res > 0) {
			msg="메뉴얼 추가 성공";
			url = "manual";
		}else {
			msg="메뉴얼 추가 실페";
			url = "insertManual";
		}
		req.setAttribute("footerContent", Includes.getFooter());
		ModelAndView mav = new ModelAndView("message");
		mav.addObject("msg", msg);
		mav.addObject("url", url);
		return mav;
	}
	@RequestMapping(value="/deleteManual", method=RequestMethod.GET)
	public String deleteManualForm(HttpServletRequest req) {
		List<ManualDTO> videoManual = manualService.getManual(0);
		List<ManualDTO> documentManual = manualService.getManual(1);
		
		req.setAttribute("videoManual", setDate(videoManual));
		req.setAttribute("documentManual", setDate(documentManual));
		req.setAttribute("footerContent", Includes.getFooter());
		return "manual/deleteForm";
	}
	@RequestMapping(value = "/deleteManual", method = RequestMethod.POST)
	public ModelAndView deleteManualPro(HttpServletRequest req) {
		int res = -1;
		String msg,url;
		if(req.getParameterValues("nums") != null) {
			List<Integer> nums = new ArrayList<Integer>();
			for(String i: req.getParameterValues("nums")) {
				nums.add(Integer.parseInt(i));
			}
			res = manualService.deleteManual(nums);
		}
		if(res > 0) {
			msg="메뉴얼 삭제 성공";
			url="manual";
		} else if(res < 0) {
			msg = "삭제할 메뉴얼을 선택해주세요";
			url="deleteManual";
		}
		else {
			msg="메뉴얼 삭제 실페";
			url="main";
		}
		ModelAndView mav = new ModelAndView("message");
		mav.addObject("msg", msg);
		mav.addObject("url", url);
		return mav;
	}
}
