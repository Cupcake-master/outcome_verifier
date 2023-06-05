package ru.itis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.service.ModuleServiceImpl;

@Controller
public class ModulesController {

    private final ModuleServiceImpl moduleService;

    public ModulesController(ModuleServiceImpl moduleService) {
        this.moduleService = moduleService;
    }

    @GetMapping("/modules")
    public String getModules(ModelMap modelMap){
        modelMap.put("modules", moduleService.findAll());
        return "module";
    }
}
