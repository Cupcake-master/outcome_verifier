package ru.itis.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Map;

@Service
public interface KeywordCounterService {
    Map<String, Integer> calculateKeywordCounts(File file);
}
