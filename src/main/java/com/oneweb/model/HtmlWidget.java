package com.oneweb.model;

public class HtmlWidget extends Widget {
	private String html;
	
	public HtmlWidget() {
		super(WidgetType.HTML);
	}
	
	public HtmlWidget(int id, String name, int width, int height, String cssClass, String cssStyle, String text,
			int order, String html) {
		super(id, name, width, height, cssClass, cssStyle, text, order, WidgetType.HTML);
		this.html = html;
	}

	public HtmlWidget(String name, int width, int height, String cssClass, String cssStyle, String text, int order, String html) {
		super(name, width, height, cssClass, cssStyle, text, order, WidgetType.HTML);
		this.html = html;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}
}
