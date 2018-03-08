package com.oneweb.model;

import java.util.HashMap;
import java.util.Map;

public enum Role {
	OWNER(1), ADMIN(2), WRITER(3), EDITOR(4), REVIEWER(5) ;
	
	private int dbValue;
	
	private Role(int dbValue) {
		this.dbValue=dbValue;
	}

	public int getDbValue() {
		return dbValue;
	}
	
	private static final Map<Integer,Role> lookup =  new HashMap<Integer,Role>();
	static {
		for(Role r : Role.values())
			lookup.put(r.getDbValue(), r);
	}

	public static Role get(int num) {
		return lookup.get(num);
	}
	
}
