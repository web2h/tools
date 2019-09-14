package com.web2h.tools.string;

import org.apache.commons.lang3.RandomStringUtils;

public class StringTools {

	public static String format(String template, Object... params) {
		String formattedString = template;
		for (Object param : params) {
			if (param != null) {
				formattedString = formattedString.replaceFirst("\\{\\}", param.toString());
			} else {
				formattedString = formattedString.replaceFirst("\\{\\}", "");
			}
		}
		return formattedString;
	}

	public static String hideSensitiveField(String sensitiveData) {
		if (sensitiveData == null) {
			return null;
		}
		return sensitiveData.replaceAll(".", "*");
	}

	public static String random(int length) {
		return RandomStringUtils.random(length, true, true);
	}
}
