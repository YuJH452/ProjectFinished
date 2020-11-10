package org.covid19.ourapp.VO;

import java.sql.Date;


public class BoardVO {
	private int bno;
	private String nickName;
	private String title;
	private String content;
	private String area;
	private Date writeDate;
	
	public BoardVO() {}
	
	public BoardVO(int bno, String nickName, String title, String content, String area, Date writeDate) {
		super();
		this.bno = bno;
		this.nickName = nickName;
		this.title = title;
		this.content = content;
		this.area = area;
		this.writeDate = writeDate;
	}
	
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public Date getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}
	@Override
	public String toString() {
		return "BoardVO [bno=" + bno + ", nickName=" + nickName + ", title=" + title + ", content=" + content
				+ ", area=" + area + ", writeDate=" + writeDate + "]";
	}
	
	
}
