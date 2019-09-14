package com.web2h.tools.string;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class StringToolsUnitTest {

	private static final String TEMPLATE = "This is a {} {} formatting a {}";

	private static final String PARAM1 = "template";

	private static final String PARAM2 = "for";

	private static final String PARAM3 = "string";

	@Test
	public void givenTemplateWithThreeSpotsAndThreeParamsWhenFormatThenAllStringIsFormatted() {
		// Given
		String expectedString = "This is a template for formatting a string";

		// When
		String formattedString = StringTools.format(TEMPLATE, PARAM1, PARAM2, PARAM3);

		// Then
		assertEquals(expectedString, formattedString);
	}
}