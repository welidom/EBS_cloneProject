package com.kgitbank.ebs.model;

public class FaqDTO {
	private String category;
	private int cno;
	private String question;
	private String answer;
	private int bno;
	private int rNum;
	private int readcount;
	
	public int getreadcount() {
		return readcount;
	}
	public void setreadcount(int readcount) {
		this.readcount = readcount;
	}

	public int getrNum() {
		return rNum;
	}
	public void setrNum(int rNum) {
		this.rNum = rNum;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	
}
