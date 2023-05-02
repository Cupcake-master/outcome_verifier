package ru.itis.controller;

import liquibase.pro.packaged.D;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.model.Repository;
import ru.itis.model.Task;
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

    private final RequirementServiceImpl requirementService;

    @Autowired
    public RepositoryController(RepositoryServiceImpl repositoryService, UserServiceImpl userService,
                                SquadServiceImpl squadService, CheckGitRepositoriesServiceImpl checkGitRepositoriesService,
                                KeywordCounterServiceImpl keywordCounterService, CountJavaKeywordsImpl countJavaKeywords,
                                RequirementServiceImpl requirementService) {
        this.repositoryService = repositoryService;
        this.userService = userService;
        this.squadService = squadService;
        this.checkGitRepositoriesService = checkGitRepositoriesService;
        this.keywordCounterService = keywordCounterService;
        this.countJavaKeywords = countJavaKeywords;
        this.requirementService = requirementService;
    }

    @GetMapping("/repository")
    public String getForm(){
        return "repository";
    }

    @PostMapping("/repository")
    public String cloneRepository(Authentication authentication, Repository repository) {
        try {
            repository.setCreated(new Date());
            repository.setSquad_id(squadService.findByName("11-906").get());
            repository.setUser_id(userService.findUserByAuthentication(authentication));
            Map<Boolean, List<Task>> solvedAndUnsolvedTasks = repositoryService.handler(repository);
            List<File> files = checkGitRepositoriesService
                    .findJavaFiles(repository.getStorage_path());
            Map<String, Integer> map = keywordCounterService.calculateKeywordCounts(files);
            countJavaKeywords.saveCountJavaKeywordsByRepository(repository, map);
            requirementService.checkRepositoryAgainstEvaluationCriteria(solvedAndUnsolvedTasks, map);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return "repository";
    }
}
