package ru.itis.service;

import org.springframework.stereotype.Service;
import ru.itis.model.Task;

import java.util.List;

@Service
public interface TaskService {
    List<Task> findAll();
}
