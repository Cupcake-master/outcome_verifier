package ru.itis.utils.visitors;

import com.github.javaparser.ast.expr.SuperExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.utils.KeywordsCounter;

@Component
public class SuperKeywordVisitor extends VoidVisitorAdapter<Void> {

    private final KeywordsCounter counter;

    @Autowired
    public SuperKeywordVisitor(KeywordsCounter counter) {
        this.counter = counter;
    }

    @Override
    public void visit(SuperExpr se, Void arg) {
        counter.addKeyword("super");
        super.visit(se, arg);
    }
}
