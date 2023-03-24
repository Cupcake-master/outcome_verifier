package ru.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.service.UserServiceImpl;

@Controller
@RequestMapping("/token")
public class ConfirmController {

    private final UserServiceImpl userService;

    @Autowired
    public ConfirmController(UserServiceImpl userService) {
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
