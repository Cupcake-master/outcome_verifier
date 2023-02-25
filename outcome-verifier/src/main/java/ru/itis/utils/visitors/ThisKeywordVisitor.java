package ru.itis.utils.visitors;

import com.github.javaparser.ast.expr.ThisExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.utils.KeywordsCounter;

@Component
public class ThisKeywordVisitor extends VoidVisitorAdapter<Void> {

    private final KeywordsCounter counter;

    @Autowired
    public ThisKeywordVisitor(KeywordsCounter counter) {
        this.counter = counter;
    }

    @Override
    public void visit(ThisExpr te, Void arg) {
        counter.addKeyword("this");
        super.visit(te, arg);
    }
}
