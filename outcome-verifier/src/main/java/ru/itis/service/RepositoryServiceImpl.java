package ru.itis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.model.Repository;
import ru.itis.repository.RepositoryRepository;

import java.io.File;
import java.util.List;
import java.util.Optional;

@Service
public class RepositoryServiceImpl {

    private final RepositoryRepository repositoryRepository;
    private final CloneGitRepositoriesServiceImpl cloneGitRepositoriesService;
    private final CheckGitRepositoriesServiceImpl checkGitRepositoriesService;

    @Autowired
    public RepositoryServiceImpl(RepositoryRepository repositoryRepository, CloneGitRepositoriesServiceImpl cloneGitRepositoriesService, CheckGitRepositoriesServiceImpl checkGitRepositoriesService) {
        this.repositoryRepository = repositoryRepository;
        this.cloneGitRepositoriesService = cloneGitRepositoriesService;
        this.checkGitRepositoriesService = checkGitRepositoriesService;
    }

    public Repository save(Repository repository){
        return repositoryRepository.save(repository);
    }

    public void handler(Repository repository){
        cloneGitRepositoriesService.clone(repository.getGit_path());
        File file = cloneGitRepositoriesService.securityCheck();
        repository.setStorage_path(file.getPath());
        List<File> files = checkGitRepositoriesService.findJavaFiles(file.getPath());
        checkGitRepositoriesService.compileJavaFiles(files, file.getPath(), repository);
        save(repository);
    }
}
