package com.oneweb.model;

import java.sql.Date;
import java.util.List;

public class Page {
	private int id;
	private String title;
	private String description;
	private Date created;
	private Date updated;
	private int views;
	private List<Widget> widgets;
	
	public Page() {
		super();
	}
	
	public Page(int id, String title, String description, Date created, Date updated, int views, List<Widget> widgets) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.created = created;
		this.updated = updated;
		this.views = views;
		this.widgets = widgets;
	}

	public Page(String title, String description, int views) {
		super();
		this.title = title;
		this.description = description;
		this.views = views;
	}

	public int getId() {
		return id;
	}
	public String getTitle() {
		return title;
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
	public int getViews() {
		return views;
	}
	public List<Widget> getWidgets() {
		return widgets;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public void setViews(int views) {
		this.views = views;
	}
	public void setWidgets(List<Widget> widgets) {
		this.widgets = widgets;
	}
	
}
