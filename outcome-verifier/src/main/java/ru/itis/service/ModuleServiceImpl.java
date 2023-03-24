package ru.itis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.model.Module;
import ru.itis.repository.ModuleRepository;

import java.util.List;

@Service
public class ModuleServiceImpl {

    private final ModuleRepository moduleRepository;

    @Autowired
    public ModuleServiceImpl(ModuleRepository moduleRepository) {
        this.moduleRepository = moduleRepository;
    }

    public List<Module> findAll(){
        return moduleRepository.findAll();
    }
}
