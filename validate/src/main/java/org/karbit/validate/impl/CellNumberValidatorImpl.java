package org.karbit.validate.impl;

import java.util.Objects;
import java.util.regex.Pattern;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.karbit.validate.CellNumber;

public class CellNumberValidatorImpl implements ConstraintValidator<CellNumber, String> {
	private static final Pattern CELL_NUMBER_REGEX = Pattern.compile("^09[0-9]{9}$");

	@Override
	public boolean isValid(String inputValue, ConstraintValidatorContext constraintValidatorContext) {
		if (Objects.isNull(inputValue)) {
			return false;
		}
		return CELL_NUMBER_REGEX.matcher(inputValue).matches();
	}
}
