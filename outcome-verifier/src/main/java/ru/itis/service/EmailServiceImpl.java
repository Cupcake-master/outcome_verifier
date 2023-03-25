package ru.itis.service;

import ru.itis.config.SenderConfig;
import ru.itis.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

@Service
@EnableAsync
public class EmailServiceImpl{

    private final SenderConfig sender;

    @Value("${email.subject}")
    private String subjectEmail;

    @Value("${email.textRight}")
    private String textRight;

    @Autowired
    public EmailServiceImpl(SenderConfig sender) {
        this.sender = sender;
    }


    public void sendConfirmation(User user) {
        String mailText = "<a href='localhost:8080/token/" + user.getToken() + "'>" + textRight + "</a>";
        sender.send(subjectEmail, mailText, user.getEmail());
    }
}
