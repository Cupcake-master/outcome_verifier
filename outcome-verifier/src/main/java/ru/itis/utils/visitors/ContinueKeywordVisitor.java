package ru.itis.utils.visitors;

import com.github.javaparser.ast.stmt.ContinueStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.utils.KeywordsCounter;

@Component
public class ContinueKeywordVisitor extends VoidVisitorAdapter<Void> {

    private final KeywordsCounter keywordsCounter;

    @Autowired
    public ContinueKeywordVisitor(KeywordsCounter keywordsCounter) {
        this.keywordsCounter = keywordsCounter;
    }

    @Override
    public void visit(ContinueStmt n, Void arg) {
        keywordsCounter.addKeyword("continue");
        super.visit(n, arg);
    }
}
