package com.kgitbank.ebs;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.oreilly.servlet.MultipartRequest;

@Controller
public class UploadController {

	
	@PostMapping("/uploadTest.do")
	public void fileUpload(HttpServletRequest request) {
		String savePath = "D:/manualFiles";
		System.out.println("Ω√¿€");
		MultipartRequest multi = null;
		try {
			multi = new MultipartRequest(request, savePath);
			System.out.println(multi.getOriginalFileName("file"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(multi.getOriginalFileName("file"));
		System.out.println("≥°");
	}
	
}
