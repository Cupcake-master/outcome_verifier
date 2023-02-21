package ru.itis.service.impl;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.service.KeywordCounterService;
import ru.itis.utils.KeywordVisitor;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class KeywordCounterServiceImpl implements KeywordCounterService {

    private final JavaParser javaParser = new JavaParser();

    @Override
    public Map<String, Integer> calculateKeywordCounts(File file) {
        Map<String, Integer> keywordCounts = new HashMap<>();
        try {
            CompilationUnit cu = javaParser.parse(file).getResult().get();
            KeywordVisitor visitor = new KeywordVisitor();
            visitor.visit(cu, null);
            keywordCounts = visitor.getKeywordCounts();
            Set<String> keywords = keywordCounts.keySet();
            for (String keyword : keywords) {
                int count = keywordCounts.get(keyword);
                System.out.println(keyword + ": " + count);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return keywordCounts;
    }
}
