package com.oneweb.model;

import java.util.HashMap;
import java.util.Map;

public enum Privilege {
	CREATE(1), READ(2), UPDATE(3), DELETE(4);
	
	private int dbValue;
	
	private Privilege(int dbValue) {
		this.dbValue=dbValue;
	}

	public int getDbValue() {
		return dbValue;
	}
	
	private static final Map<Integer,Privilege> lookup =  new HashMap<Integer, Privilege>();
	static {
		for(Privilege p : Privilege.values())
			lookup.put(p.getDbValue(), p);
	}

	public static Privilege get(int num) {
		return lookup.get(num);
	}
	
}