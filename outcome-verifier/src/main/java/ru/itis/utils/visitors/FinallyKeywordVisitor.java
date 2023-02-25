package ru.itis.utils.visitors;

import com.github.javaparser.ast.stmt.TryStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.utils.KeywordsCounter;

@Component
public class FinallyKeywordVisitor extends VoidVisitorAdapter<Void> {

    private final KeywordsCounter counter;

    @Autowired
    public FinallyKeywordVisitor(KeywordsCounter counter) {
        this.counter = counter;
    }

    @Override
    public void visit(TryStmt ts, Void arg) {
        if (ts.getFinallyBlock().isPresent()) {
            counter.addKeyword("finally");
        }
        super.visit(ts, arg);
    }
}
