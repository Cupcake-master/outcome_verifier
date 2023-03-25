package ru.itis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.model.Repository;
import ru.itis.model.StatusTask;
import ru.itis.model.Task;
import ru.itis.repository.StatusTaskRepository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class StatusTaskServiceImpl {

    private final StatusTaskRepository statusTaskRepository;

    @Autowired
    public StatusTaskServiceImpl(StatusTaskRepository statusTaskRepository) {
        this.statusTaskRepository = statusTaskRepository;
    }

    public StatusTask save(StatusTask statusTask){
        return statusTaskRepository.save(statusTask);
    }

    public void saveSolvedAndUnresolvedTasks(Map<Boolean, List<Task>> solvedAndUnSolvedTasks, Repository repository){
        List<Task> solvedTasks = solvedAndUnSolvedTasks.get(true);
        List<Task> unsolvedTasks = solvedAndUnSolvedTasks.get(false);
        solvedTasks.stream().map(task -> StatusTask.builder()
                .repository_id(repository)
                .isSolved(true)
                .task(task)
                .build()).forEachOrdered(statusTask -> {
            statusTask.setCreated(new Date());
            statusTask.setUpdated(new Date());
            save(statusTask);
        });
        unsolvedTasks.stream().map(task -> StatusTask.builder()
                .repository_id(repository)
                .isSolved(true)
                .task(task)
                .build()).forEachOrdered(statusTask -> {
            statusTask.setCreated(new Date());
            statusTask.setUpdated(new Date());
            save(statusTask);
        });
    }
}
