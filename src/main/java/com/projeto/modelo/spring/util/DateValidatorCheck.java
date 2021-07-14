package com.projeto.modelo.spring.util;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateValidatorCheck implements ConstraintValidator<IDateValidatorCheck, String> {

    private String value;

    @Override
    public void initialize(IDateValidatorCheck constraintAnnotation) {
        this.value = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {

        if (value == null) {
            return false;
        }
        var dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        if (value.trim().length() != dateFormat.toPattern().length()) {
            return false;
        }
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(value.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

}