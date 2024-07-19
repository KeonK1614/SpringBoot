package com.study.springboot.bean;

public class Member {
	private String name;
	private String nickname;
	private Printer printer;
	
	// 기본 생성자
	// 매개변수가 있는 생성자가 있으면 반드시 선언해야함.
	public Member() {}
	
	public Member(String name, String nickname, Printer printer) {
		this.name = name;
		this.nickname = nickname;
		this.printer = printer;
	}


	public void setName(String name) {
		this.name = name;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setPrinter(Printer printer) {
		this.printer = printer;
	}
	
	public void print() {
		printer.print("Hello " + name + " : " + nickname);
	}
	
}
