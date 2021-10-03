package com.kgitbank.ebs;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;

import com.oreilly.servlet.MultipartRequest;

public class UploadController {

	@PostMapping("/uploadTest")
	public void fileUpload(HttpServletRequest request) {
		
		try {
			MultipartRequest multi = new MultipartRequest(request, "D:/manualFiles/abcdefg");
		} catch (IOException e) {			
			e.printStackTrace();
		}
		
	}
	
}
