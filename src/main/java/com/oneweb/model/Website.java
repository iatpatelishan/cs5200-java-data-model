package com.oneweb.model;

import java.sql.Date;
import java.util.List;

public class Website {
	
	private int id;
	private String name;
	private String description;
	private Date created;
	private Date updated;
	private int visits;
	private List<Page> pages;
	
	

	public Website() {
		super();
	}
	
	public Website(int id, String name, String description, Date created, Date updated, int visits, List<Page> pages) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.created = created;
		this.updated = updated;
		this.visits = visits;
		this.pages = pages;
	}

	public Website(String name, String description, int visits) {
		super();
		this.name = name;
		this.description = description;
		this.visits = visits;
	}

	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public Date getCreated() {
		return created;
	}
	public Date getUpdated() {
		return updated;
	}
	public int getVisits() {
		return visits;
	}
	public List<Page> getPages() {
		return pages;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public void setVisits(int visits) {
		this.visits = visits;
	}
	public void setPages(List<Page> pages) {
		this.pages = pages;
	}	
	
}
