package ru.tokarev.shop.controller.validation;

import org.springframework.beans.factory.annotation.Autowired;
import ru.tokarev.shop.service.user.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NumberPhoneNotRegisteredValidator implements ConstraintValidator<NumberPhoneNotRegistered, String> {

    private final UserService userService;

    @Autowired
    public NumberPhoneNotRegisteredValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(NumberPhoneNotRegistered constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !userService.existsUserByNumberPhone(value);
    }
}
