package ru.itis.utils;

import ru.itis.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.itis.service.SquadServiceImpl;
import ru.itis.service.UserServiceImpl;

@Component
public class UserValidator implements Validator {

    private final UserServiceImpl userService;

    private final SquadServiceImpl squadService;

    @Autowired
    public UserValidator(UserServiceImpl userService, SquadServiceImpl squadService) {
        this.userService = userService;
        this.squadService = squadService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        if (userService.findByEmail(user.getEmail()).isPresent()) {
            errors.rejectValue(
                    "email", "This email is already in use", "This email is already in use"
            );
        }
    }
}
