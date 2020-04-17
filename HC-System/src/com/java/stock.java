package com.java;


import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class stock {
	
	private int id;
	private String mname;
	private String description;
	private int amount;
	private int price;
	
	
	
public stock() {
		
	}
	
	public stock(int id, String mname, String description, int amount, int price) {
		super();
		this.id = id;
		this.mname = mname;
		this.description = description;
		this.amount = amount;
		this.price = price;
	}
	
	
	
	
	
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

}
