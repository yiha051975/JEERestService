package com.sheldon.rest.common.validation.annotations;

import com.sheldon.rest.common.validation.validators.CurrencyValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Sheld on 6/22/2017.
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = CurrencyValidator.class)
public @interface Currency {
    String message() default "Invalid Currency Format";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
