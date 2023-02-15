package ru.itis.controller;

import ru.itis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/token")
public class ConfirmController {

    private final UserService userService;

    @Autowired
    public ConfirmController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{token}")
    public String checkToken(@PathVariable("token") String token){
        if (userService.confirm(token)) {
            return "redirect:/developers";
        }
        return "/signIn";
    }
}
