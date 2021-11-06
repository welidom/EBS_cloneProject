package com.kgitbank.ebs;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.io.ByteStreams;

@Controller
public class FileController {
	@RequestMapping(value = "/downloadFile")
	public void downloadFile(HttpServletResponse resp, HttpServletRequest req,String originalFileName, String path) throws Exception {
		String root_path = req.getSession().getServletContext().getRealPath("/");  
		String filePath = root_path+"resources/Files/"+path+"/"+originalFileName;
	     
	    String docName = URLEncoder.encode(originalFileName,"UTF-8").replaceAll("\\+", "%20");
	    resp.setHeader("Content-Disposition", "attachment;filename=" + docName + ";");
	    resp.setContentType("text/plain");
	 
	    File down_file = new File(filePath);
	    FileInputStream fileIn = new FileInputStream(down_file);
	    ByteStreams.copy(fileIn, resp.getOutputStream());
	    resp.flushBuffer();
	}
}
