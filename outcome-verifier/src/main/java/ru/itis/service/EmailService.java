package ru.itis.service;

import ru.itis.model.User;
import org.springframework.stereotype.Service;

@Service
public interface EmailService {
    void sendConfirmation(User user);
}
