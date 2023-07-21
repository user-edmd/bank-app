//package com.edmond.bank.validation;
//
//import jakarta.validation.ConstraintValidator;
//import jakarta.validation.ConstraintValidatorContext;
//import org.joda.time.DateTime;
//
//public class BirthDateConstraintValidator implements ConstraintValidator<BirthDateValidator, String> {
//
//    @Override
//    public void initialize(BirthDateValidator birthDateValidator) {
//    }
//
//    @Override
//    public boolean isValid(String input, ConstraintValidatorContext constraintValidatorContext) {
//        DateTime currentDate = DateTime.now();
//        DateTime birthDate = DateTime.parse(input);
//
//        if (currentDate.minusYears(18).isAfter(birthDate)) {
//            return true;
//        }
//        return false;
//    }
//}
