package com.oneweb.model;

import java.sql.Date;
import java.util.List;

public class User extends Person {
	private boolean userAgreement;
	private String userKey;
	
	public User() {
		super();
	}
	
	public User(int id, String firstName, String lastName, String username, String password, String email,
			Date dateOfBirth, List<Phone> phones, List<Address> addresses, boolean userAgreement, String userKey) {
		super(id, firstName, lastName, username, password, email, dateOfBirth, phones, addresses);
		this.userAgreement = userAgreement;
		this.userKey = userKey;
	}

	public User(String firstName, String lastName, String username, String password, String email,
			String userKey) {
		super(firstName, lastName, username, password, email);
		this.userAgreement = true;
		this.userKey = userKey;
	}

	public boolean isUserAgreement() {
		return userAgreement;
	}
	public void setUserAgreement(boolean userAgreement) {
		this.userAgreement = userAgreement;
	}
	public String getUserKey() {
		return userKey;
	}
	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}
	
	
}
