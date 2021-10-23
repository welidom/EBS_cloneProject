package com.kgitbank.ebs;



import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kgitbank.ebs.model.FaqDTO;
import com.kgitbank.ebs.service.faqMapper;

@Controller
public class FaqController {
	@Inject
	private faqMapper faqmapper;
	
	@RequestMapping(value="/faqList.do", method=RequestMethod.GET)
	public String list(Model model, FaqDTO dto, String keyword) throws Exception{
		model.addAttribute("listCategory", faqmapper.FaqlistCategory());
		if(dto.getCno() != 1000) {
		model.addAttribute("readTitle", faqmapper.FaqreadTitle(dto.getCno(), keyword));
		model.addAttribute("totalBnoCount", faqmapper.FaqtotalBnoCount(dto.getCno(), keyword));
		} else {
		model.addAttribute("none", "1000");
		model.addAttribute("readTitle", faqmapper.Faqfavorite());
		model.addAttribute("totalBnoCount", faqmapper.FaqFavoriteTotalBnoCount(dto.getCno(), keyword));
		}
		
		return "faq/listView";
	}
	
}