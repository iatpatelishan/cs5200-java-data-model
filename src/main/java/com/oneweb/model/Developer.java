package com.oneweb.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Developer extends Person {
	
	private String developerKey;	
	List<Website> websites;
	
	public Developer() {
		super();
	}
	
	public Developer(int id, String firstName, String lastName, String username, String password, String email,
			Date dateOfBirth, List<Phone> phones, List<Address> addresses, String developerKey,
			List<Website> websites) {
		super(id, firstName, lastName, username, password, email, dateOfBirth, phones, addresses);
		this.developerKey = developerKey;
		this.websites = websites;
	}

	public Developer(String firstName, String lastName, String username, String password, String email,
			String developerKey) {
		super(firstName, lastName, username, password, email);
		this.developerKey = developerKey;
	}

	public String getDeveloperKey() {
		return developerKey;
	}
	public List<Website> getWebsites() {
		return websites;
	}
	public void setDeveloperKey(String developerKey) {
		this.developerKey = developerKey;
	}
	public void setWebsites(List<Website> websites) {
		this.websites = websites;
	}
	public void addWebsite(Website website) {
		if(this.websites==null) {
			this.websites = new ArrayList<Website>();
		}
	    this.websites.add(website);
	}

	@Override
	public String toString() {
		return "Developer [developerKey=" + developerKey + ", websites=" + websites + ", getUsername()=" + getUsername()
				+ "]";
	}
}
