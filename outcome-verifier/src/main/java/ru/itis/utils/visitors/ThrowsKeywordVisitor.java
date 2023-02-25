package ru.itis.utils.visitors;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import org.springframework.stereotype.Component;
import ru.itis.utils.KeywordsCounter;

@Component
public class ThrowsKeywordVisitor extends VoidVisitorAdapter<Void> {

    private final KeywordsCounter counter;

    public ThrowsKeywordVisitor(KeywordsCounter counter) {
        this.counter = counter;
    }

    @Override
    public void visit(MethodDeclaration md, Void arg) {
        if (!md.getThrownExceptions().isEmpty()) {
            counter.addKeyword("throws");
        }
        super.visit(md, arg);
    }
}
