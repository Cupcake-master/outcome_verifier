package ru.itis.utils.visitors;

import com.github.javaparser.ast.stmt.ReturnStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.utils.KeywordsCounter;

@Component
public class ReturnKeywordVisitor extends VoidVisitorAdapter<Void> {

    private final KeywordsCounter keywordsCounter;

    @Autowired
    public ReturnKeywordVisitor(KeywordsCounter keywordsCounter) {
        this.keywordsCounter = keywordsCounter;
    }

    @Override
    public void visit(ReturnStmt n, Void arg) {
        keywordsCounter.addKeyword("return");
        super.visit(n, arg);
    }
}
