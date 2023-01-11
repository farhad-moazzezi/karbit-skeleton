package org.karbit.validate;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.karbit.validate.impl.CellNumberValidatorImpl;


@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CellNumberValidatorImpl.class)
@Target(value = { ElementType.FIELD, ElementType.TYPE, ElementType.PARAMETER })
@Documented
public @interface CellNumber {

	String message() default "invalid cellNumber!";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}

