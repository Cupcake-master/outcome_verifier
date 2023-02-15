package ru.itis.service;

import ru.itis.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {
    Optional<User> findByEmail(String email);

    Optional<User> findByToken(String token);

    void signUp(User user);

    User save(User user);

    boolean confirm(String token);
}
