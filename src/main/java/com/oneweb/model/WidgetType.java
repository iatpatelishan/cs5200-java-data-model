package com.oneweb.model;

import java.util.HashMap;
import java.util.Map;

public enum WidgetType {
	YOUTUBE("youtube"), IMAGE("image"), HTML("html"), HEADING("heading");
	
	private String dbValue;
	
	private WidgetType(String dbValue) {
		this.dbValue=dbValue;
	}

	public String getDbValue() {
		return dbValue;
	}
	
	private static final Map<String,WidgetType> lookup =  new HashMap<String, WidgetType>();
	static {
		for(WidgetType w : WidgetType.values())
			lookup.put(w.getDbValue(), w);
	}

	public static WidgetType get(String str) {
		return lookup.get(str);
	}
}