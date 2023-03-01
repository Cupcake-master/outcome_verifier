package ru.itis.service;

import org.springframework.stereotype.Service;
import ru.itis.model.Repository;
import ru.itis.model.User;

@Service
public interface RepositoryService {

    Repository save(Repository repository);
}
