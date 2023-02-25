package ru.itis.utils.visitors;

import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.utils.KeywordsCounter;

import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;

@Component
public class AccessModifierKeywordVisitor extends VoidVisitorAdapter<Void> {

    private final KeywordsCounter keywordsCounter;

    @Autowired
    public AccessModifierKeywordVisitor(KeywordsCounter keywordsCounter) {
        this.keywordsCounter = keywordsCounter;
    }

    @Override
    public void visit(MethodDeclaration methodDeclaration, Void arg) {
        if (methodDeclaration.isPrivate()) {
            keywordsCounter.addKeyword("private");
        } else if (methodDeclaration.isProtected()) {
            keywordsCounter.addKeyword("protected");
        } else if (methodDeclaration.isPublic()) {
            keywordsCounter.addKeyword("public");
        }
        super.visit(methodDeclaration, arg);
    }

    @Override
    public void visit(FieldDeclaration fieldDeclaration, Void arg) {
        if (fieldDeclaration.isPrivate()) {
            keywordsCounter.addKeyword("private");
        } else if (fieldDeclaration.isProtected()) {
            keywordsCounter.addKeyword("protected");
        } else if (fieldDeclaration.isPublic()) {
            keywordsCounter.addKeyword("public");
        }
        super.visit(fieldDeclaration, arg);
    }

}
