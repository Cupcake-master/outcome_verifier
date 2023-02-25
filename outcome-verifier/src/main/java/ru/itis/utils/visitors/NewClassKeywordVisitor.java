package ru.itis.utils.visitors;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.utils.KeywordsCounter;

import java.util.List;

@Component
public class NewClassKeywordVisitor extends VoidVisitorAdapter<Void> {

    private final KeywordsCounter keywordsCounter;

    @Autowired
    public NewClassKeywordVisitor(KeywordsCounter keywordsCounter) {
        this.keywordsCounter = keywordsCounter;
    }

    @Override
    public void visit(MethodDeclaration methodDeclaration, Void arg) {
        BlockStmt methodBody = methodDeclaration.getBody().orElse(null);

        if (methodBody != null) {
            List<SimpleName> newKeywords = methodBody.findAll(SimpleName.class, n -> n.asString().equals("new"));
            List<SimpleName> classKeywords = methodBody.findAll(SimpleName.class, n -> n.asString().equals("class"));
            newKeywords.stream().map(simpleName -> "new").forEach(keywordsCounter::addKeyword);
            classKeywords.stream().map(simpleName -> "class").forEach(keywordsCounter::addKeyword);
        }
        super.visit(methodDeclaration, arg);
    }
}
