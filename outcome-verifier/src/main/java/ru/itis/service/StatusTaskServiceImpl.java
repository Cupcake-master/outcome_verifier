package ru.itis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.model.StatusTask;
import ru.itis.repository.StatusTaskRepository;

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

    public void saveSolvedAndUnresolvedTasks(){

    }
}
