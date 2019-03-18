package com.web2h.tools.date;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.Test;

public class DateToolsUnitTest {

	@Test
	public void givenSameDatesWhenIsAfterThenReturnsFalse() {

		// Given
		Date date1 = new Date();
		Date date2 = new Date(date1.getTime());

		// When
		boolean isAfter = DateTools.isAfter(date1, date2);

		// Then
		assertFalse(isAfter);
	}

	@Test
	public void givenDate1NotAfterDate2WhenIsAfterThenReturnsFalse() {

		// Given
		Date date1 = new Date();
		Date date2 = new Date(date1.getTime() + 100);

		// When
		boolean isAfter = DateTools.isAfter(date1, date2);

		// Then
		assertFalse(isAfter);
	}

	@Test
	public void givenDate1AfterDate2WhenIsAfterThenReturnsTrue() {

		// Given
		Date date1 = new Date();
		Date date2 = new Date(date1.getTime() - 100);

		// When
		boolean isAfter = DateTools.isAfter(date1, date2);

		// Then
		assertTrue(isAfter);
	}

	@Test
	public void givenSameDatesWhenIsBeforeThenReturnsFalse() {

		// Given
		Date date1 = new Date();
		Date date2 = new Date(date1.getTime());

		// When
		boolean isBefore = DateTools.isBefore(date1, date2);

		// Then
		assertFalse(isBefore);
	}

	@Test
	public void givenDate1NotBeforeDate2WhenIsBeforeThenReturnsFalse() {

		// Given
		Date date1 = new Date();
		Date date2 = new Date(date1.getTime() - 100);

		// When
		boolean isBefore = DateTools.isBefore(date1, date2);

		// Then
		assertFalse(isBefore);
	}

	@Test
	public void givenDate1BeforeDate2WhenIsBeforeThenReturnsTrue() {

		// Given
		Date date1 = new Date();
		Date date2 = new Date(date1.getTime() + 100);

		// When
		boolean isBefore = DateTools.isBefore(date1, date2);

		// Then
		assertTrue(isBefore);
	}
}
