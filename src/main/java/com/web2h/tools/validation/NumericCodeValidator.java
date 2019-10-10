package com.web2h.tools.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.math.NumberUtils;

public class NumericCodeValidator implements ConstraintValidator<NumericCode, String> {

	private int length;

	@Override
	public void initialize(NumericCode constraint) {
		this.length = constraint.length();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value == null || value.length() == length && NumberUtils.isDigits(value);
	}
}