package ru.itis.service.impl;

import org.springframework.stereotype.Service;
import ru.itis.service.CheckGitRepositoriesService;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CheckGitRepositoriesServiceImpl implements CheckGitRepositoriesService {

    @Override
    public List<File> findJavaFiles(String path) {
        File dir = new File(path);
        File[] arrFiles = dir.listFiles();
        List<File> javaFiles = Arrays.stream(Objects.requireNonNull(arrFiles))
                .filter(x -> x.getName().endsWith(".java"))
                .collect(Collectors.toList());
        System.out.println(javaFiles);
        return javaFiles;
    }

    @Override
    public void compileJavaFiles(List<File> files, String path) {
        try {
            File pathFile = new File(path);
            Process proc;
            for (File file: files) proc = Runtime.getRuntime()
                    .exec("javac " + file.getName(), null, pathFile);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
