package com.java;

import java.sql.Date;
import java.sql.Time;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class appoiment {
	private int Id;
	private String dname;
	private String pname;
	private Date date;
	private Time time;
	
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}
	

	
}
