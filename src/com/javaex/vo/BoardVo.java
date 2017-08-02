package com.javaex.vo;

public class BoardVo {
	
	private int no;
	private int user_no;
	private int hit_number;
	private String name;
	private String date;
	private String content;
	private String title;

	public BoardVo() {
		
	}

	public BoardVo(int user_no, String title, String content) {
		
	}
	
	public BoardVo(int no, int user_no, int hit_number, String name, String date, String content, String title) {
		super();
		this.no = no;
		this.user_no = user_no;
		this.hit_number = hit_number;
		this.name = name;
		this.date = date;
		this.content = content;
		this.title = title;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	public int getHit_number() {
		return hit_number;
	}

	public void setHit_number(int hit_number) {
		this.hit_number = hit_number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "BoardVo [no=" + no + ", user_no=" + user_no + ", hit_number=" + hit_number + ", name=" + name
				+ ", date=" + date + ", content=" + content + ", title=" + title + "]";
	}
	

}
