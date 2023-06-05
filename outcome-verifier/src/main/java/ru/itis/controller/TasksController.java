package ru.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.service.TaskServiceImpl;

@Controller
public class TasksController {

    private final TaskServiceImpl taskService;

    @Autowired
    public TasksController(TaskServiceImpl taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public String getTasks(ModelMap modelMap){
        modelMap.put("tasks", taskService.findAll());
        return "task";
    }

}
