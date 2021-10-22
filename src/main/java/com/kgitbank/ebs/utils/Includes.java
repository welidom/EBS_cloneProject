package com.kgitbank.ebs.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class Includes {
	public static String[][] getFooter() {
		String[][] footerContent =  {{"logo01", ""},{"logo02", ""},{"logo03", ""},{"logo04", ""},{"logo05", ""},{"logo06", ""},{"logo07", ""},{"logo08", ""},{"logo09", ""}};
		return footerContent;
	}
	public static void saveFile(MultipartFile file, String dirPath) throws IOException {
		Path dir = Paths.get(dirPath).toAbsolutePath().normalize();

		Files.createDirectories(dir);
	    
		String fileName = new String(file.getOriginalFilename().getBytes("8859_1"), "utf-8");

		Path targetPath = dir.resolve(fileName).normalize();

		file.transferTo(targetPath);
	}
}
