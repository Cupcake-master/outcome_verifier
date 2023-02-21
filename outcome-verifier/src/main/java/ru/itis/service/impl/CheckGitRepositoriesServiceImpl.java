package ru.itis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.model.Task;
import ru.itis.model.Test;
import ru.itis.service.CheckGitRepositoriesService;
import ru.itis.service.TaskService;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CheckGitRepositoriesServiceImpl implements CheckGitRepositoriesService {

    private final TaskService taskService;

    @Autowired
    public CheckGitRepositoriesServiceImpl(TaskService taskService) {
        this.taskService = taskService;
    }

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
            for (File file: files) {
                Process compile = runConsoleCommand("javac " + file.getName(), pathFile);
                shutDownProcess(compile);
                int indexEnd = file.getName().indexOf('.');
                Process run = runConsoleCommand("java " + file.getName().substring(0, indexEnd) + " 400", pathFile);
                String result = new BufferedReader(new InputStreamReader(run.getInputStream()))
                        .lines().collect(Collectors.joining("\n"));
                System.out.println(result);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        List<Task> tasks = taskService.findAll();
        List<Test> tests = new ArrayList<>();
        for (Task task: tasks) {
            tests.addAll(task.getTests());
        }
        System.out.println(tests);

    }

    public Process runConsoleCommand(String command, File pathFile){
        Process process;
        try {
            process = Runtime.getRuntime()
                    .exec(command, null, pathFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return process;
    }

    public void shutDownProcess(Process process){
        try {
            process.waitFor();
            process.destroy();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
