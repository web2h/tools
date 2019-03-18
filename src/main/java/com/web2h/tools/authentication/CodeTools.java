package com.web2h.tools.authentication;

import org.apache.commons.lang3.RandomStringUtils;

public class CodeTools {

	public static String buildAuthenticationCode() {
		StringBuffer code = new StringBuffer();

		code.append(getRandomLetters(1))
				.append(getRandomDigits(2))
				.append(" ")
				.append(getRandomLetters(1))
				.append(getRandomDigits(2));

		return code.toString();
	}

	private static String getRandomLetters(int length) {
		return RandomStringUtils.random(length, "ABCDEFGHJKLMNPQRSTUVWXYZ");
	}

	private static String getRandomDigits(int length) {
		return RandomStringUtils.random(length, "123456789");
	}
}
