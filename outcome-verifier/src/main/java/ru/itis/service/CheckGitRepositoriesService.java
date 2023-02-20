package ru.itis.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public interface CheckGitRepositoriesService {

    List<File> findJavaFiles(String path);

    void compileJavaFiles(List<File> files, String path);
}
