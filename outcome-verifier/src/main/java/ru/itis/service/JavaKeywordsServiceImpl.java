package ru.itis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.model.Grade;
import ru.itis.model.JavaKeywords;
import ru.itis.model.Module;
import ru.itis.repository.JavaKeywordsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class JavaKeywordsServiceImpl {

    private final JavaKeywordsRepository javaKeywordsRepository;

    @Autowired
    public JavaKeywordsServiceImpl(JavaKeywordsRepository javaKeywordsRepository) {
        this.javaKeywordsRepository = javaKeywordsRepository;
    }

    public List<JavaKeywords> findAll() {
        return javaKeywordsRepository.findAll();
    }

    public Optional<JavaKeywords> findByName(String name) {
        return javaKeywordsRepository.findByName(name);
    }

    public List<JavaKeywords> findByModuleAndGrade(Module module, Grade grade){
        return javaKeywordsRepository.findByModuleAndGrade(module, grade);
    }
}
