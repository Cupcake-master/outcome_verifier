package ru.itis.utils.visitors;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.utils.KeywordsCounter;

@Component
public class StaticKeywordVisitor extends VoidVisitorAdapter<Void> {

    private final KeywordsCounter keywordsCounter;

    @Autowired
    public StaticKeywordVisitor(KeywordsCounter keywordsCounter) {
        this.keywordsCounter = keywordsCounter;
    }

    @Override
    public void visit(FieldDeclaration fieldDeclaration, Void arg) {
        if (fieldDeclaration.isStatic()) {
            keywordsCounter.addKeyword("static");
        }
        super.visit(fieldDeclaration, arg);
    }

    @Override
    public void visit(MethodDeclaration methodDeclaration, Void arg) {
        if (methodDeclaration.isStatic()) {
            keywordsCounter.addKeyword("static");
        }
        super.visit(methodDeclaration, arg);
    }

    @Override
    public void visit(ClassOrInterfaceDeclaration classDeclaration, Void arg) {
        if (classDeclaration.isStatic()) {
            keywordsCounter.addKeyword("static");
        }
        super.visit(classDeclaration, arg);
    }
}
