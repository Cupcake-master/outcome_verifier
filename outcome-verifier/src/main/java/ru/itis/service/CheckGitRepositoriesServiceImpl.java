package ru.itis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.model.Repository;
import ru.itis.model.Task;
import ru.itis.model.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class CheckGitRepositoriesServiceImpl {

    private final TaskServiceImpl taskService;

    @Autowired
    public CheckGitRepositoriesServiceImpl(TaskServiceImpl taskService) {
        this.taskService = taskService;
    }

    public List<File> findJavaFiles(String path) {
        File dir = new File(path);
        File[] arrFiles = dir.listFiles();
        List<File> javaFiles = Arrays.stream(Objects.requireNonNull(arrFiles))
                .filter(x -> x.getName().endsWith(".java"))
                .collect(Collectors.toList());
        System.out.println(javaFiles);
        return javaFiles;
    }

    public void compileJavaFiles(List<File> files, String path, Repository repository) {
        List<Task> tasks = taskService.findAll();
        List<Test> tests = getAllTests(tasks);

        try {
            File pathFile = new File(path);
            for (File file : files) {
                compileJavaFile(file, pathFile);
                Task task = findTaskByFile(tasks, file);
                List<Test> testByTask = filterTestsByTask(tests, task);
                int successTest = runTestsAndGetSuccessCount(testByTask, file, pathFile);
                printResults(task, successTest, testByTask.size());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<Test> getAllTests(List<Task> tasks) {
        return tasks.stream().flatMap(task -> task.getTests().stream()).collect(Collectors.toList());
    }

    private void compileJavaFile(File file, File pathFile) throws IOException, InterruptedException {
        Process compile = runConsoleCommand("javac " + file.getName(), pathFile);
        shutDownProcess(compile);
    }

    private List<Test> filterTestsByTask(List<Test> tests, Task task) {
        return tests.stream().filter(x -> x.getTask_id().getId().equals(task.getId())).collect(Collectors.toList());
    }

    private int runTestsAndGetSuccessCount(List<Test> testByTask, File file, File pathFile) {
        int successTest = 0;
        for (Test test : testByTask) {
            String result = runTestAndGetResult(file, pathFile, test);
            if (result.equals(test.getOutput())) {
                successTest++;
            } else {
                printError(test, result);
            }
        }
        return successTest;
    }

    private String runTestAndGetResult(File file, File pathFile, Test test) {
        Process run = runConsoleCommand("java " +
                file.getName().substring(0, file.getName().indexOf('.')) + " " + test.getInput(), pathFile);
        return new BufferedReader(new InputStreamReader(run.getInputStream()))
                .lines().collect(Collectors.joining("\n"));
    }

    private void printError(Test test, String result) {
        System.err.println(test.getTask_id() + "." + test.getId() + " result: " + result + "   " + test.getInput());
        System.err.println("actual: " + test.getOutput());
    }

    private void printResults(Task task, int successTest, int testCount) {
        String output = String.format("Task: %d - number of tests passed %d out of %d",
                task.getId(), successTest, testCount);
        System.out.println(output);
    }

    private Process runConsoleCommand(String command, File pathFile) {
        Process process;
        try {
            process = Runtime.getRuntime().exec(command, null, pathFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return process;
    }

    private void shutDownProcess(Process process) {
        try {
            process.waitFor();
            process.destroy();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private Task findTaskByFile(List<Task> tasks, File file) {
        int indexEnd = file.getName().indexOf('.');
        String nameFile = file.getName().substring(0, indexEnd);
        long numberTask = extractNumberFromFileName(nameFile);
        return tasks.stream().filter(x -> x.getId().equals(numberTask)).findFirst().orElse(null);
    }

    private static int extractNumberFromFileName(String fileName) {
        Pattern pattern = Pattern.compile("Task_(\\d+)");
        Matcher matcher = pattern.matcher(fileName);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }
        return 0;
    }


}
