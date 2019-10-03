package com.web2h.tools.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

// TODO Adapt validator so that it can take country as a parameter
public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {

	@Override
	public void initialize(PhoneNumber phoneNumber) {
	}

	@Override
	public boolean isValid(String phoneNumber, ConstraintValidatorContext cxt) {
		return phoneNumber == null || phoneNumber != null && phoneNumber.matches("[0-9]{10}") && (phoneNumber.startsWith("06") || phoneNumber.startsWith("07"));
	}
}