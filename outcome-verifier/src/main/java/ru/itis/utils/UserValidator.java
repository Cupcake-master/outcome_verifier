package ru.itis.utils;

import ru.itis.model.User;
import ru.itis.service.SquadService;
import ru.itis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    private final UserService userService;

    private final SquadService squadService;

    @Autowired
    public UserValidator(UserService userService, SquadService squadService) {
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
