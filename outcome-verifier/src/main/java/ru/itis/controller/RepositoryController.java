package ru.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.model.Repository;
import ru.itis.service.CheckGitRepositoriesServiceImpl;
import ru.itis.service.CloneGitRepositoriesServiceImpl;
import ru.itis.service.RepositoryServiceImpl;

import java.io.File;
import java.util.Date;
import java.util.List;

@Controller
public class RepositoryController {

    private final RepositoryServiceImpl repositoryService;
    private final CloneGitRepositoriesServiceImpl cloneGitRepositoriesService;
    private final CheckGitRepositoriesServiceImpl checkGitRepositoriesService;

    @Autowired
    public RepositoryController(RepositoryServiceImpl repositoryService, CloneGitRepositoriesServiceImpl cloneGitRepositoriesService, CheckGitRepositoriesServiceImpl checkGitRepositoriesService) {
        this.repositoryService = repositoryService;
        this.cloneGitRepositoriesService = cloneGitRepositoriesService;
        this.checkGitRepositoriesService = checkGitRepositoriesService;
    }

    @GetMapping("/index")
    public String cloneRepository() {
        cloneGitRepositoriesService.clone("https://github.com/Cupcake-master/glowing-eureka.git");
        File file = cloneGitRepositoriesService.securityCheck();
        List<File> files = checkGitRepositoriesService.findJavaFiles(file.getPath());
        checkGitRepositoriesService.compileJavaFiles(files, file.getPath());
        Repository repository = Repository.builder()
                .name("Bulat")
                .surname("Bilalov")
                .patronymic("Foatovich")
                .created(new Date())
                .git_path("https://github.com/Cupcake-master/glowing-eureka.git")
                .storage_path(file.getPath())
                .build();
        repositoryService.save(repository);
        return "index";
    }
}
