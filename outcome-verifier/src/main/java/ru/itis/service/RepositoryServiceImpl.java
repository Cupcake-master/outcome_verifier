package ru.itis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.model.Repository;
import ru.itis.repository.RepositoryRepository;

@Service
public class RepositoryServiceImpl {

    private final RepositoryRepository repositoryRepository;

    @Autowired
    public RepositoryServiceImpl(RepositoryRepository repositoryRepository) {
        this.repositoryRepository = repositoryRepository;
    }

    public Repository save(Repository repository){
        return repositoryRepository.save(repository);
    }
}
