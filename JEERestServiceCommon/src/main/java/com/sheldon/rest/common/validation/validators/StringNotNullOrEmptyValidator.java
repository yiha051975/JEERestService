package com.sheldon.rest.common.validation.validators;

import com.sheldon.rest.common.validation.annotations.StringNotNullOrEmpty;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Sheld on 6/22/2017.
 */
public class StringNotNullOrEmptyValidator implements ConstraintValidator<StringNotNullOrEmpty, String> {
    @Override
    public void initialize(StringNotNullOrEmpty constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return (null != value && !value.trim().isEmpty());
    }
}
