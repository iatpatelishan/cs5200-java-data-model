package com.oneweb.model;

public class Phone {
	private int id;
	private String number;
	private boolean primary;
	
	public int getId() {
		return id;
	}
	public String getNumber() {
		return number;
	}
	public boolean getPrimary() {
		return primary;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public void setPrimary(boolean primary) {
		this.primary = primary;
	}
	
}
