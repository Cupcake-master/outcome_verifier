package ru.itis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.model.CountJavaKeywords;
import ru.itis.model.JavaKeywords;
import ru.itis.model.Repository;
import ru.itis.repository.CountJavaKeywordsRepository;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Service
public class CountJavaKeywordsImpl {

    private final CountJavaKeywordsRepository countJavaKeywordsRepository;
    private final JavaKeywordsServiceImpl javaKeywordsService;

    @Autowired
    public CountJavaKeywordsImpl(CountJavaKeywordsRepository countJavaKeywordsRepository, JavaKeywordsServiceImpl javaKeywordsService) {
        this.countJavaKeywordsRepository = countJavaKeywordsRepository;
        this.javaKeywordsService = javaKeywordsService;
    }

    private CountJavaKeywords save(CountJavaKeywords countJavaKeywords){
        return countJavaKeywordsRepository.save(countJavaKeywords);
    }

    public void saveCountJavaKeywordsByRepository(Repository repository,
                                                  Map<String, Integer> javaKeyWordsCount){
        javaKeyWordsCount.forEach((key, value) -> {
            Optional<JavaKeywords> javaKeywords = javaKeywordsService.findByName(key);
            if (javaKeywords.isPresent()) {
                CountJavaKeywords countJavaKeywords = CountJavaKeywords
                        .builder()
                        .repository_id(repository)
                        .javaKeywords_id(javaKeywords.get())
                        .count(value)
                        .build();
                countJavaKeywords.setCreated(new Date());
                countJavaKeywords.setUpdated(new Date());
                save(countJavaKeywords);
            }
        });
    }
}
