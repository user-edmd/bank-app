package com.edmond.bank.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = BirthDateConstraintValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD } )
@Retention(RetentionPolicy.RUNTIME)
public @interface BirthDateValidator {
    public String message() default "Must be 18 years or older.";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default{};
}
