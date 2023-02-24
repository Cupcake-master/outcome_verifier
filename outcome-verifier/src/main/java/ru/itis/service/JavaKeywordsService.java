package ru.itis.service;

import org.springframework.stereotype.Service;
import ru.itis.model.JavaKeywords;

import java.util.List;
import java.util.Optional;

@Service
public interface JavaKeywordsService {
    List<JavaKeywords> findAll();
    Optional<JavaKeywords> findByName(String name);
}
