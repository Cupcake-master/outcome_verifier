package ru.itis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.model.JavaKeywords;
import ru.itis.repository.JavaKeywordsRepository;
import ru.itis.service.JavaKeywordsService;

import java.util.List;
import java.util.Optional;

@Service
public class JavaKeywordsServiceImpl implements JavaKeywordsService {

    private final JavaKeywordsRepository javaKeywordsRepository;

    @Autowired
    public JavaKeywordsServiceImpl(JavaKeywordsRepository javaKeywordsRepository) {
        this.javaKeywordsRepository = javaKeywordsRepository;
    }

    @Override
    public List<JavaKeywords> findAll() {
        return javaKeywordsRepository.findAll();
    }

    @Override
    public Optional<JavaKeywords> findByName(String name) {
        return javaKeywordsRepository.findByName(name);
    }
}
