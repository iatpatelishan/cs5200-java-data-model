package com.oneweb.model;

public class HeadingWidget extends Widget {
	private int size;
	
	public HeadingWidget() {
		super(WidgetType.HEADING);
	}
	
	public HeadingWidget(int id, String name, int width, int height, String cssClass, String cssStyle, String text,
			int order, int size) {
		super(id, name, width, height, cssClass, cssStyle, text, order, WidgetType.HEADING);
		this.size = size;
	}
	
	public HeadingWidget(String name, int width, int height, String cssClass, String cssStyle, String text, int order,
			WidgetType type, int size) {
		super(name, width, height, cssClass, cssStyle, text, order, WidgetType.HEADING);
		this.size = size;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	
	
}
