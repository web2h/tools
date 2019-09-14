package com.web2h.tools.log;

public class LogTools {

	public static boolean different(Object object1, Object object2) {
		if (object1 == null && object2 == null) {
			return false;
		}
		return object1 == null || object2 == null || !object1.equals(object2);
	}
}