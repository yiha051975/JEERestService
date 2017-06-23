package com.sheldon.rest.common.validation.validators;

import com.sheldon.rest.common.validation.annotations.Currency;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * Created by Sheld on 6/22/2017.
 */
public class CurrencyValidator implements ConstraintValidator<Currency, Double> {
    private static final String CURRENCY_REGEX = "^\\d+(\\.\\d{1,2})?$";
    private static final Pattern CURRENCY_PATTERN = Pattern.compile(CURRENCY_REGEX);

    @Override
    public void initialize(Currency constraintAnnotation) {

    }

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        return (null != value && CURRENCY_PATTERN.matcher(value.toString()).matches());
    }
}
