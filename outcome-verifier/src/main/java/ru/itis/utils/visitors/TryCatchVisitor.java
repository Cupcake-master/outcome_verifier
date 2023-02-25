package ru.itis.utils.visitors;

import com.github.javaparser.ast.stmt.CatchClause;
import com.github.javaparser.ast.stmt.TryStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.utils.KeywordsCounter;

@Component
public class TryCatchVisitor extends VoidVisitorAdapter<Void> {
    private final KeywordsCounter keywordsCounter;

    @Autowired
    public TryCatchVisitor(KeywordsCounter keywordsCounter) {
        this.keywordsCounter = keywordsCounter;
    }

    @Override
    public void visit(TryStmt n, Void arg) {
        keywordsCounter.addKeyword("try");
        super.visit(n, arg);
    }

    @Override
    public void visit(CatchClause n, Void arg) {
        keywordsCounter.addKeyword("catch");
        super.visit(n, arg);
    }
}
