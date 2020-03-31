package ru.tokarev.shop.controller.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NumberPhoneNotRegisteredValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NumberPhoneNotRegistered {
    String message() default "User with this number phone already registered";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
