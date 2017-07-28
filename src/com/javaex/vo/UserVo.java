package com.javaex.vo;

public class UserVo {

	private int no;
	private String name;
	private String email;
	private String pass;
	private String gender;
	
	public UserVo() {}
	
	
	
	public UserVo(String name, String email, String pass, String gender) {
		super();
		this.name = name;
		this.email = email;
		this.pass = pass;
		this.gender = gender;
	}

	public UserVo(int no, String name, String email, String pass, String gender) {
		super();
		this.no = no;
		this.name = name;
		this.email = email;
		this.pass = pass;
		this.gender = gender;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "UserVo [no=" + no + ", name=" + name + ", email=" + email + ", pass=" + pass + ", gender=" + gender
				+ "]";
	}
	
	
	
}
