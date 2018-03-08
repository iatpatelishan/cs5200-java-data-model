package com.oneweb.model;

public class Widget {
	private int id;
	private String name;
	private int width;
	private int height;
	private String cssClass;
	private String cssStyle;
	private String text;
	private int order;
	private WidgetType type;
	
	public Widget(WidgetType type) {
		this.type = type;
	}
	
	public Widget(int id, String name, int width, int height, String cssClass, String cssStyle, String text, int order,
			WidgetType type) {
		super();
		this.id = id;
		this.name = name;
		this.width = width;
		this.height = height;
		this.cssClass = cssClass;
		this.cssStyle = cssStyle;
		this.text = text;
		this.order = order;
		this.type = type;
	}
	
	public Widget(String name, int width, int height, String cssClass, String cssStyle, String text, int order,
			WidgetType type) {
		super();
		this.name = name;
		this.width = width;
		this.height = height;
		this.cssClass = cssClass;
		this.cssStyle = cssStyle;
		this.text = text;
		this.order = order;
		this.type = type;
	}

	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public String getCssClass() {
		return cssClass;
	}
	public String getCssStyle() {
		return cssStyle;
	}
	public String getText() {
		return text;
	}
	public int getOrder() {
		return order;
	}
	public WidgetType getType() {
		return type;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}
	public void setCssStyle(String cssStyle) {
		this.cssStyle = cssStyle;
	}
	public void setText(String text) {
		this.text = text;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public void setType(WidgetType type) {
		this.type = type;
	}
			
}