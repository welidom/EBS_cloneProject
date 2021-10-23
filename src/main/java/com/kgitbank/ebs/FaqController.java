package com.kgitbank.ebs;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kgitbank.ebs.model.FaqDTO;
import com.kgitbank.ebs.service.faqMapper;

@Controller
@RequestMapping("/board/*")
public class FaqController {

	@Inject
	private faqMapper faqmapper;
	
	@RequestMapping(value="/board/list", method=RequestMethod.GET)
	public String list(Model model, FaqDTO dto, String keyword) throws Exception{
		model.addAttribute("listCategory", faqmapper.FaqlistCategory());
		if(dto.getCno() != 1000) {
		model.addAttribute("readTitle", faqmapper.FaqreadTitle(dto.getCno(), keyword));
		model.addAttribute("totalBnoCount", faqmapper.FaqtotalBnoCount(dto.getCno(), keyword));
		} else {
		model.addAttribute("none", "1000");
		model.addAttribute("readTitle", faqmapper.Faqfadtorite());
		model.addAttribute("totalBnoCount", faqmapper.FaqFadtoriteTotalBnoCount(dto.getCno(), keyword));
		}
		
		
		
		return "/board/listView";
	}
	
	@RequestMapping(value="/board/list", method=RequestMethod.POST)
	public String lists(Model model, FaqDTO dto, String keyword) throws Exception{
		model.addAttribute("listCategory", faqmapper.FaqlistCategory());
		if(dto.getCno() != 1000) {
		model.addAttribute("readTitle", faqmapper.FaqreadTitle(dto.getCno(), keyword));
		model.addAttribute("totalBnoCount", faqmapper.FaqtotalBnoCount(dto.getCno(), keyword));
		} else {
		model.addAttribute("none", "1000");
		model.addAttribute("readTitle", faqmapper.Faqfadtorite());
		model.addAttribute("totalBnoCount", faqmapper.FaqFadtoriteTotalBnoCount(dto.getCno(), keyword));
		}
		
		return "/board/listView";
	}
	
	
	
	@RequestMapping(value="/download", method=RequestMethod.GET)
	public void download(HttpServletResponse response) throws Exception{
		try {
        	String path = "D:\\09_10 Java-WebProject_JGJ\\test.txt"; 
        	
        	File file = new File(path);
        	response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
        	
        	FileInputStream fileInputStream = new FileInputStream(path);
        	OutputStream out = response.getOutputStream();
        	
        	int read = 0;
                byte[] buffer = new byte[1024];
                while ((read = fileInputStream.read(buffer)) != -1) {
                    out.write(buffer, 0, read);
                }
                
        } catch (Exception e) {
            throw new Exception("download error");
        }	
	}
	
	@RequestMapping(value="/board/newqu", method=RequestMethod.GET)
	public String newquView() throws Exception {
		return "/board/newQuPage";
	}
	
	@RequestMapping(value="/board/newqu", method=RequestMethod.POST)
	public String newqu(FaqDTO dto) throws Exception{
		faqmapper.FaqnewQuestion(dto.getCategory(), dto.getCno(), dto.getQuestion(), dto.getAnswer());
		int recno = dto.getCno();
		return "redirect:/board/list?cno=" + recno;
	}
	
	@RequestMapping(value="/board/delete", method=RequestMethod.POST)
	public String delete(FaqDTO dto) throws Exception{
		faqmapper.Faqdelete(dto.getCno(), dto.getBno());
		int recno = dto.getCno();
		return "redirect:/board/list?cno=" + recno;
	}
	
	@RequestMapping(value="/board/update", method=RequestMethod.POST)
	public String updateView(FaqDTO dto, Model model) throws Exception{
		model.addAttribute("update", faqmapper.FaqupdateView(dto.getCno(), dto.getBno()));
		return "/board/updateView";
	}
	
	@RequestMapping(value="/board/updateQu", method=RequestMethod.POST)
	public String update(FaqDTO dto) throws Exception{
		faqmapper.Faqupdate(dto.getCno(), dto.getCategory(), dto.getQuestion(), dto.getAnswer(), dto.getBno());
		int recno = dto.getCno();
		return "redirect:/board/list?cno=" + recno;
	}
	
	@ResponseBody
	@RequestMapping(value="/board/readcount", method=RequestMethod.GET)
	public void readcounts(@RequestParam(value="cno") int cno, @RequestParam(value="bno") int bno) throws Exception{
		faqmapper.Faqreadcount(cno, bno);
	}
	
}
