package ru.itis.service.impl;

import org.springframework.stereotype.Service;
import ru.itis.service.CloneGitRepositoriesService;

import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CloneGitRepositoriesServiceImpl implements CloneGitRepositoriesService {

    private final String GIT_COMMAND;
    private final String TARGET_PATH;
    private String REPOSITORY_NAME;
    private final AtomicInteger id = new AtomicInteger(0);

    public CloneGitRepositoriesServiceImpl() {
        TARGET_PATH = "C:\\Projects\\temp";
        GIT_COMMAND = String.format("git -C %s clone ", TARGET_PATH);
    }

    @Override
    public void clone(String path) {
        try {
            REPOSITORY_NAME = "git-check-" + id.get();
            String clone_command = GIT_COMMAND + path + " " + REPOSITORY_NAME;
            Process proc = Runtime.getRuntime().exec(clone_command);
            proc.waitFor();
            proc.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void securityCheck() {
        File folder = new File(TARGET_PATH);

        if(folder.exists()) {
            if(folder.canRead() && folder.canWrite()) {
                System.out.println("The folder is readable and writable");
            } else {
                System.out.println("The folder is not readable and writable");
            }
        } else {
            System.out.println("The folder does not exist");
        }
    }
}
