package ru.itis.service.impl;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.springframework.stereotype.Service;
import ru.itis.service.KeywordCounterService;
import ru.itis.utils.KeywordVisitor;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class KeywordCounterServiceImpl implements KeywordCounterService {

    private final JavaParser javaParser = new JavaParser();

    @Override
    public Map<String, Integer> calculateKeywordCounts(List<File> files) {
        Map<String, Integer> result = new HashMap<>();
        for (File file: files) {
            Map<String, Integer> keywordCounts;
            try {
                CompilationUnit cu = javaParser.parse(file).getResult().get();
                KeywordVisitor visitor = new KeywordVisitor();
                visitor.visit(cu, null);
                keywordCounts = visitor.getKeywordCounts();
                joinTwoHashMap(result, keywordCounts);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return result;
    }

    public void joinTwoHashMap(Map<String, Integer> result,
                               Map<String, Integer> supplementary){
        for (Map.Entry<String, Integer> entry : supplementary.entrySet()) {
            if (result.containsKey(entry.getKey())){
                int previousValue = result.get(entry.getKey());
                result.put(entry.getKey(), previousValue + entry.getValue());
            }else{
                result.put(entry.getKey(), entry.getValue());
            }
        }
    }
}
