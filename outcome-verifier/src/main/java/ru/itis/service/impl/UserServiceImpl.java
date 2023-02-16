package ru.itis.service.impl;

import liquibase.pro.packaged.D;
import ru.itis.model.Role;
import ru.itis.model.Squad;
import ru.itis.model.State;
import ru.itis.model.User;
import ru.itis.repository.UserRepository;
import ru.itis.service.RoleService;
import ru.itis.service.SquadService;
import ru.itis.service.StateService;
import ru.itis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    private final SquadService squadService;
    private final StateService stateService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService, PasswordEncoder passwordEncoder, SquadService squadService, StateService stateService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.squadService = squadService;
        this.stateService = stateService;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findByToken(String token) {
        return userRepository.findByToken(token);
    }

    @Override
    public void signUp(User user, String squad_name) {
        Optional<Role> roleUser = roleService.findByName("ROLE_STUDENT");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setToken(generateNewToken());
        user.setUpdated(new Date());
        user.setCreated(new Date());
        roleUser.ifPresent(role -> user.setRoles(Collections.singletonList(role)));
        stateService.findByName("NOT_ACTIVE").ifPresent(user::setState);
        squadService.findByName(squad_name).ifPresent(user::setSquad_id);
        save(user);
    }

    private static String generateNewToken() {
        int length = 500;
        Random r = new Random();
        return r.ints(48, 122)
                .filter(i -> (i < 57 || i > 65) && (i < 90 || i > 97))
                .mapToObj(i -> (char) i)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean confirm(String token) {
        Optional<User> optionalUser = findByToken(token);
        if (optionalUser.isPresent()) {
            stateService.findByName("ACTIVE").ifPresent(state -> optionalUser.get().setState(state));
            save(optionalUser.get());
            return true;
        } else {
            return false;
        }
    }
}
