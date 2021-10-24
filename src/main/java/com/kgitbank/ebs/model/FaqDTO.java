package com.kgitbank.ebs.model;

public class FaqDTO {
	private int num;
	private int category;
	private String question;
	private String answer;
	private int readcount;
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
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
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	@Override
	public String toString() {
		return "FaqDTO [num=" + num + ", category=" + category + ", question=" + question + ", answer=" + answer
				+ ", readcount=" + readcount + "]";
	}
	
	
}
