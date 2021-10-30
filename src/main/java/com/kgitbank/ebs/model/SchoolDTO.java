package com.kgitbank.ebs.model;

public class SchoolDTO {
	private String id;
	private String pw;
	private String manager;
	private String name;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "SchoolDTO [id=" + id + ", pw=" + pw + ", manager=" + manager + ", name=" + name + "]";
	}
	
}
