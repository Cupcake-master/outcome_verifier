package ru.itis.utils.visitors;

import com.github.javaparser.ast.expr.ObjectCreationExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.utils.KeywordsCounter;

@Component
public class NewKeywordVisitor  extends VoidVisitorAdapter<Void> {

    private final KeywordsCounter keywordsCounter;

    @Autowired
    public NewKeywordVisitor(KeywordsCounter keywordsCounter) {
        this.keywordsCounter = keywordsCounter;
    }

    @Override
    public void visit(ObjectCreationExpr n, Void arg) {
        keywordsCounter.addKeyword("new");
        super.visit(n, arg);
    }
}
