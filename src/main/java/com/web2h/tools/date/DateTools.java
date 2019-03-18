package com.web2h.tools.date;

import java.util.Date;

import org.apache.commons.lang3.Validate;

public class DateTools {

	public static boolean isAfter(Date date1, Date date2) {

		Validate.notNull(date1, "Date 1 should not be null when calling DateTools.isAfter");
		Validate.notNull(date2, "Date 2 should not be null when calling DateTools.isAfter");
		return date1.getTime() > date2.getTime();
	}

	public static boolean isBefore(Date date1, Date date2) {

		Validate.notNull(date1, "Date 1 should not be null when calling DateTools.isBefore");
		Validate.notNull(date2, "Date 2 should not be null when calling DateTools.isBefore");
		return date1.getTime() < date2.getTime();
	}
}
