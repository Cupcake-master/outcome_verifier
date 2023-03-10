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
import java.util.*;
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
        List<Task> tasks = taskService.findAll();
        List<Test> tests = tasks.stream().flatMap(task -> task.getTests().stream()).collect(Collectors.toList());
        HashMap<File, Task> fileTaskHashMap = new HashMap<>();
        try {
            File pathFile = new File(path);
            for (File file: files) {
                Process compile = runConsoleCommand("javac " + file.getName(), pathFile);
                shutDownProcess(compile);
                Task task = findTaskByFile(tasks, file);
                fileTaskHashMap.put(file, task);
                List<Test> testByTask = tests.stream().filter(x -> x.getTask_id().getId().equals(task.getId()))
                        .collect(Collectors.toList());
                int successTest = 0;
                for (Test test: testByTask) {
                    Process run = runConsoleCommand("java " +
                            file.getName().substring(0, file.getName().indexOf('.')) + " " + test.getInput(), pathFile);
                    String result = new BufferedReader(new InputStreamReader(run.getInputStream()))
                            .lines().collect(Collectors.joining("\n"));
                    if (result.equals(test.getOutput())){
                        successTest++;
                    }else{
                        System.err.println(test.getTask_id() + "." + test.getId() + " result: " + result + "   " + test.getInput());
                        System.err.println("actual: " + test.getOutput());
                    }
                }
                String output = String.format("Task: %d - number of tests passed %d out of %d",
                        task.getId(), successTest, testByTask.size());
                System.out.println(output);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Task findTaskByFile(List<Task> tasks, File file){
        int indexEnd = file.getName().indexOf('.');
        String nameFile = file.getName().substring(0, indexEnd);
        long numberTask = Long.parseLong(String.valueOf(nameFile.charAt(nameFile.length() - 1)));
        return tasks.stream().filter(x -> x.getId().equals(numberTask)).findFirst().orElse(null);
    }

    private Process runConsoleCommand(String command, File pathFile){
        Process process;
        try {
            process = Runtime.getRuntime()
                    .exec(command, null, pathFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return process;
    }

    private void shutDownProcess(Process process){
        try {
            process.waitFor();
            process.destroy();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
