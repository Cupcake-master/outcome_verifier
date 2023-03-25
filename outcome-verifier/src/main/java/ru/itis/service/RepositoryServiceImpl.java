package ru.itis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.model.Repository;
import ru.itis.model.Task;
import ru.itis.repository.RepositoryRepository;

import java.io.File;
import java.util.List;
import java.util.Map;

@Service
public class RepositoryServiceImpl {

    private final RepositoryRepository repositoryRepository;
    private final CloneGitRepositoriesServiceImpl cloneGitRepositoriesService;
    private final CheckGitRepositoriesServiceImpl checkGitRepositoriesService;

    private final StatusTaskServiceImpl statusTaskService;

    @Autowired
    public RepositoryServiceImpl(RepositoryRepository repositoryRepository, CloneGitRepositoriesServiceImpl cloneGitRepositoriesService, CheckGitRepositoriesServiceImpl checkGitRepositoriesService, StatusTaskServiceImpl statusTaskService) {
        this.repositoryRepository = repositoryRepository;
        this.cloneGitRepositoriesService = cloneGitRepositoriesService;
        this.checkGitRepositoriesService = checkGitRepositoriesService;
        this.statusTaskService = statusTaskService;
    }

    public Repository save(Repository repository){
        return repositoryRepository.save(repository);
    }

    public void handler(Repository repository){
        cloneGitRepositoriesService.clone(repository.getGit_path());
        File file = cloneGitRepositoriesService.securityCheck();
        repository.setStorage_path(file.getPath());
        List<File> files = checkGitRepositoriesService.findJavaFiles(file.getPath());
        Map<Boolean, List<Task>> map = checkGitRepositoriesService
                .compileJavaFiles(files, file.getPath());
        statusTaskService.saveSolvedAndUnresolvedTasks(map, repository);
        save(repository);
    }
}
