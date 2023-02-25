package ru.itis.utils.visitors;

import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.utils.KeywordsCounter;

@Component
public class FinalKeywordVisitor extends VoidVisitorAdapter<Void> {

    private final KeywordsCounter keywordsCounter;

    @Autowired
    public FinalKeywordVisitor(KeywordsCounter keywordsCounter) {
        this.keywordsCounter = keywordsCounter;
    }

    @Override
    public void visit(VariableDeclarationExpr declaration, Void arg) {
        if (declaration.isFinal()) {
            for (VariableDeclarator declarator : declaration.getVariables()) {
                if (declarator.getType().isPrimitiveType()) {
                    continue;
                }
                keywordsCounter.addKeyword("final");
            }
        }
        super.visit(declaration, arg);
    }
}
