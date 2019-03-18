package com.web2h.tools.authentication;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CodeToolsUnitTest {

	@Test
	public void whenBuildAuthenticationCodeThenGeneratedCodeMatchesExpectedFormat() {

		// When
		String authenticationCode = CodeTools.buildAuthenticationCode();

		// Then
		Assertions.assertTrue(authenticationCode.matches("^[A-Z]{1}[\\d]{2}\\s[A-Z]{1}[\\d]{2}$"));
	}
}
