package com.kgitbank.ebs.model;

public class NoticeDTO {
	private int num;
	private int mustRead;
	private String category;
	private String subject;
	private String content;
	private String attach;
	private String reg_date;
	private int readcount;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getMustRead() {
		return mustRead;
	}
	public void setMustRead(int mustRead) {
		this.mustRead = mustRead;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	@Override
	public String toString() {
		return "NoticeDTO [num=" + num + ", mustRead=" + mustRead + ", category=" + category + ", subject=" + subject
				+ ", content=" + content + ", attach=" + attach + ", reg_date=" + reg_date + ", readcount=" + readcount
				+ "]";
	}
	
	
}
