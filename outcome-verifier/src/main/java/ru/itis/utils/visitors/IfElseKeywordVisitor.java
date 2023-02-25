package ru.itis.utils.visitors;

import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.utils.KeywordsCounter;

@Component
public class IfElseKeywordVisitor extends VoidVisitorAdapter<Void> {

    private final KeywordsCounter keywordsCounter;

    @Autowired
    public IfElseKeywordVisitor(KeywordsCounter keywordsCounter) {
        this.keywordsCounter = keywordsCounter;
    }

    @Override
    public void visit(IfStmt n, Void arg) {
        keywordsCounter.addKeyword("if");
        super.visit(n, arg);
        if (n.getElseStmt().isPresent()) {
            keywordsCounter.addKeyword("else");
            super.visit(n, arg);
        }
    }
}
