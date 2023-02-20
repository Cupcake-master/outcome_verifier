package ru.itis.service;

import org.springframework.stereotype.Service;

import java.io.File;

@Service
public interface CloneGitRepositoriesService {
    void clone(String path);

    File securityCheck();
}
