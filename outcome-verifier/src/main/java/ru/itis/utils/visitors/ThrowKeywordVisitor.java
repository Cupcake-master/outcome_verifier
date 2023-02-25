package ru.itis.utils.visitors;

import com.github.javaparser.ast.stmt.ThrowStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.utils.KeywordsCounter;

@Component
public class ThrowKeywordVisitor extends VoidVisitorAdapter<Void> {

    private final KeywordsCounter counter;

    @Autowired
    public ThrowKeywordVisitor(KeywordsCounter counter) {
        this.counter = counter;
    }

    @Override
    public void visit(ThrowStmt ts, Void arg) {
        counter.addKeyword("throw");
        super.visit(ts, arg);
    }
}
