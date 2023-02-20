package ru.itis.service;

import org.springframework.stereotype.Service;

@Service
public interface CloneGitRepositoriesService {
    void clone(String path);

    void securityCheck();
}
