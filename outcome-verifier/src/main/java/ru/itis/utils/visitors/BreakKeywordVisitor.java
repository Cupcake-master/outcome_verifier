package ru.itis.utils.visitors;

import com.github.javaparser.ast.stmt.BreakStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.utils.KeywordsCounter;

@Component
public class BreakKeywordVisitor extends VoidVisitorAdapter<Void> {

    private final KeywordsCounter keywordsCounter;

    @Autowired
    public BreakKeywordVisitor(KeywordsCounter keywordsCounter) {
        this.keywordsCounter = keywordsCounter;
    }

    @Override
    public void visit(BreakStmt n, Void arg) {
        keywordsCounter.addKeyword("break");
        super.visit(n, arg);
    }
}
