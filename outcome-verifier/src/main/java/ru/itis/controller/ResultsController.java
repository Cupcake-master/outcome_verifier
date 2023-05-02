package ru.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.model.Result;
import ru.itis.service.RequirementServiceImpl;

import java.util.List;

@Controller
public class ResultsController {

    private final RequirementServiceImpl requirementService;

    @Autowired
    public ResultsController(RequirementServiceImpl requirementService) {
        this.requirementService = requirementService;
    }

    @GetMapping("/results")
    public String getResults(ModelMap model){
        List<Result> results = requirementService.getResults();
        model.put("results", results);
        return "results";
    }
}
