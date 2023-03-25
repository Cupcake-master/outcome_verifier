package ru.itis.controller;

import ru.itis.dto.CaptchaResponseDto;
import ru.itis.model.Repository;
import ru.itis.model.User;
import ru.itis.service.*;
import ru.itis.utils.Attributes;
import ru.itis.utils.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/signUp")
public class SignUpController {
    private final static String CAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s";

    private final UserValidator userValidator;
    private final RestTemplate restTemplate;
    private final UserServiceImpl userService;
    private final EmailServiceImpl emailService;

    private final SquadServiceImpl squadService;

    @Value("${recaptcha.secret}")
    private String secret;

    private CloneGitRepositoriesServiceImpl cloneGitRepositoriesService;

    private CheckGitRepositoriesServiceImpl checkGitRepositoriesService;

    @Autowired
    public SignUpController(UserValidator userValidator, RestTemplate restTemplate,
                            UserServiceImpl userService, EmailServiceImpl emailService,
                            SquadServiceImpl squadService, CloneGitRepositoriesServiceImpl cloneGitRepositoriesService,
                            CheckGitRepositoriesServiceImpl checkGitRepositoriesService) {
        this.userValidator = userValidator;
        this.restTemplate = restTemplate;
        this.userService = userService;
        this.emailService = emailService;
        this.squadService = squadService;
        this.cloneGitRepositoriesService = cloneGitRepositoriesService;
        this.checkGitRepositoriesService = checkGitRepositoriesService;
    }

    @GetMapping
    public String getRegistration(){
        return "signUp";
    }

    @PostMapping
    public String signUp(User user, BindingResult result, ModelMap model,
                         @RequestParam("g-recaptcha-response") String captchaResponse,
                         @RequestParam(name = "squad") String squad_name){
        try {
            userValidator.validate(user, result);
            StringBuilder error = errorChecking(captchaResponse, result, squad_name);
            if (error.length() == 0){
                Attributes.addSuccessAttributes(model, "A confirmation letter will come to your mail soon!");
                userService.signUp(user, squad_name);
                //emailService.sendConfirmation(user);
            }else{
                Attributes.addErrorAttributes(model, String.valueOf(error));
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        cloneGitRepositoriesService.clone("https://github.com/Cupcake-master/glowing-eureka.git");
        try {
            File file = cloneGitRepositoriesService.securityCheck();
            List<File> files = checkGitRepositoriesService.findJavaFiles(file.getPath());
            checkGitRepositoriesService.compileJavaFiles(files, file.getPath());
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return "/signUp";
    }

    private StringBuilder errorChecking(String captchaResponse, BindingResult result, String squad_name){
        StringBuilder builder = new StringBuilder();
        String url = String.format(CAPTCHA_URL, secret, captchaResponse);
        CaptchaResponseDto response = restTemplate.postForObject(url, Collections.emptyList(), CaptchaResponseDto.class);
        if (!Objects.requireNonNull(response).isSuccess()) {
            builder.append("Fill captcha! \n");
        }
        if (result.hasErrors()) {
            builder.append("This mail is already taken! \n");
        }
        if (!squadService.findByName(squad_name).isPresent()){
            builder.append("There is no such group. Contact support to create it.");
        }
        return builder;
    }
}
