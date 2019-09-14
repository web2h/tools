package com.web2h.tools.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FrenchZipCodeValidator implements ConstraintValidator<FrenchZipCode, String> {

	@Override
	public void initialize(FrenchZipCode zipCode) {
	}

	@Override
	public boolean isValid(String zipCode, ConstraintValidatorContext cxt) {
		// TODO Improve validator to match French zip code format
		return zipCode == null || zipCode != null && zipCode.matches("[0-9]{5}");
	}
}
