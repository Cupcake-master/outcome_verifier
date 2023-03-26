package ru.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.model.Repository;
import ru.itis.service.*;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class RepositoryController {

    private final RepositoryServiceImpl repositoryService;
    private final UserServiceImpl userService;

    private final SquadServiceImpl squadService;

    private final CheckGitRepositoriesServiceImpl checkGitRepositoriesService;

    private final KeywordCounterServiceImpl keywordCounterService;

    private final CountJavaKeywordsImpl countJavaKeywords;

    @Autowired
    public RepositoryController(RepositoryServiceImpl repositoryService, UserServiceImpl userService, SquadServiceImpl squadService, CheckGitRepositoriesServiceImpl checkGitRepositoriesService, KeywordCounterServiceImpl keywordCounterService, CountJavaKeywordsImpl countJavaKeywords) {
        this.repositoryService = repositoryService;
        this.userService = userService;
        this.squadService = squadService;
        this.checkGitRepositoriesService = checkGitRepositoriesService;
        this.keywordCounterService = keywordCounterService;
        this.countJavaKeywords = countJavaKeywords;
    }

    @GetMapping("/index")
    public String cloneRepository(Authentication authentication) {
        try {
            Repository repository = Repository.builder()
                    .name("Bulat")
                    .surname("Bilalov")
                    .patronymic("Foatovich")
                    .created(new Date())
                    .git_path("https://github.com/Cupcake-master/glowing-eureka.git")
                    .squad_id(squadService.findByName("11-906").get())
                    .user_id(userService.findUserByAuthentication(authentication))
                    .build();
            repositoryService.handler(repository);
            List<File> files = checkGitRepositoriesService
                    .findJavaFiles(repository.getStorage_path());
            Map<String, Integer> map = keywordCounterService.calculateKeywordCounts(files);
            countJavaKeywords.saveCountJavaKeywordsByRepository(repository, map);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return "index";
    }
}
