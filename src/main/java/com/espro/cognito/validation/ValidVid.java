package com.espro.cognito.validation;

import com.nimbusds.jose.Payload;
import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidVidValidator.class)
public @interface ValidVid {
    String message() default "Invalid VID format";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
