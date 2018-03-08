package com.oneweb.model;

import java.sql.Date;
import java.util.List;

public class Person {
	
	private int id;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String email;
	private Date dateOfBirth;
	private List<Phone> phones;
	private List<Address> addresses;
	
	public Person() {
		super();
	}
	
	public Person(int id, String firstName, String lastName, String username, String password, String email,
			Date dateOfBirth, List<Phone> phones, List<Address> addresses) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.phones = phones;
		this.addresses = addresses;
	}

	public Person(String firstName, String lastName, String username, String password, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public int getId() {
		return id;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getEmail() {
		return email;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public List<Phone> getPhones() {
		return phones;
	}
	public List<Address> getAddresses() {
		return addresses;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}
	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
}
