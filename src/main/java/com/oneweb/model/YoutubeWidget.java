package com.oneweb.model;

public class YoutubeWidget extends Widget {
	
	private String url;
	private boolean shareable;
	private boolean expandable;
	
	public YoutubeWidget() {
		super(WidgetType.YOUTUBE);
	}
	
	public YoutubeWidget(int id, String name, int width, int height, String cssClass, String cssStyle, String text,
			int order, String url, boolean shareable, boolean expandable) {
		super(id, name, width, height, cssClass, cssStyle, text, order, WidgetType.YOUTUBE);
		this.url = url;
		this.shareable = shareable;
		this.expandable = expandable;
	}

	public YoutubeWidget(String name, int width, int height, String cssClass, String cssStyle, String text, int order,
			String url, boolean shareable, boolean expandable) {
		super(name, width, height, cssClass, cssStyle, text, order, WidgetType.YOUTUBE);
		this.url = url;
		this.shareable = shareable;
		this.expandable = expandable;
	}

	public String getUrl() {
		return url;
	}
	public boolean getShareable() {
		return shareable;
	}
	public boolean getExpandable() {
		return expandable;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setShareable(boolean shareable) {
		this.shareable = shareable;
	}
	public void setExpandable(boolean expandable) {
		this.expandable = expandable;
	}
	
}
