package ru.itis.controller;

import ru.itis.service.CheckGitRepositoriesService;
import ru.itis.service.KeywordCounterService;
import ru.itis.utils.Attributes;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;
import java.util.logging.LogManager;

@Controller
public class SignInController {

    private final CheckGitRepositoriesService checkGitRepositoriesService;
    private final KeywordCounterService keywordCounterService;

    public SignInController(CheckGitRepositoriesService checkGitRepositoriesService, KeywordCounterService keywordCounterService) {
        this.checkGitRepositoriesService = checkGitRepositoriesService;
        this.keywordCounterService = keywordCounterService;
    }

    @GetMapping("/signIn")
    public String getSignIn(HttpServletRequest request, ModelMap modelMap, @RequestParam(value = "error", required = false) String error){
        LogManager.getLogManager().reset();
        HttpSession session = request.getSession(false);
        if (session != null && error != null){
            AuthenticationException ex = (AuthenticationException) session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
            if (ex != null) {
                Attributes.addErrorAttributes(modelMap, ex.getMessage());
            }
        }
        List<File> files = checkGitRepositoriesService
                .findJavaFiles("C:\\Projects\\temp\\repository-check-0");
        files.forEach(keywordCounterService::calculateKeywordCounts);
        return "signIn";
    }
}
