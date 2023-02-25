package ru.itis.utils.visitors;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.InstanceOfExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.utils.KeywordsCounter;

import java.util.List;

@Component
public class InstanceOfKeywordVisitor extends VoidVisitorAdapter<Void> {

    private final KeywordsCounter keywordsCounter;

    @Autowired
    public InstanceOfKeywordVisitor(KeywordsCounter keywordsCounter) {
        this.keywordsCounter = keywordsCounter;
    }

    @Override
    public void visit(MethodDeclaration methodDeclaration, Void arg) {
        BlockStmt methodBody = methodDeclaration.getBody().orElse(null);
        if (methodBody != null) {
            List<InstanceOfExpr> instanceOfExpressions = methodBody.findAll(InstanceOfExpr.class);
            instanceOfExpressions.stream().map(instanceOfExpr -> "instanceof").forEach(keywordsCounter::addKeyword);
        }
        super.visit(methodDeclaration, arg);
    }
}
