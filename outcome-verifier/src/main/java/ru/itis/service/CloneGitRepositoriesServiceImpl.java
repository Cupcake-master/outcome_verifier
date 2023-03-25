package ru.itis.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CloneGitRepositoriesServiceImpl{

    private String GIT_COMMAND;
    @Value("${download.target.path}")
    private String TARGET_PATH;
    @Value("${download.target.name}")
    private String REPOSITORY_NAME;
    private final AtomicInteger id = new AtomicInteger(0);

    public void clone(String path) {
        try {
            GIT_COMMAND = String.format("git -C %s clone ", TARGET_PATH);
            String clone_command = GIT_COMMAND + path + " " + REPOSITORY_NAME + id.get();
            Process proc = Runtime.getRuntime().exec(clone_command);
            proc.waitFor();
            proc.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public File securityCheck() {
        File folder = new File(TARGET_PATH + "\\" + REPOSITORY_NAME + id.get());

        if(folder.exists()) {
            if(folder.canRead() && folder.canWrite()) {
                System.out.println("The folder is readable and writable");
            } else {
                System.out.println("The folder is not readable and writable");
            }
        } else {
            System.out.println("The folder does not exist");
        }
        return folder;
    }
}
